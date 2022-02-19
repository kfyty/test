package com.kfyty.jiujia.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.kfyty.jiujia.api.DeptListApi;
import com.kfyty.jiujia.api.DoctorDateListApi;
import com.kfyty.jiujia.api.DoctorListApi;
import com.kfyty.jiujia.api.LockApi;
import com.kfyty.jiujia.api.UserListApi;
import com.kfyty.jiujia.api.model.UserDoctor;
import com.kfyty.jiujia.api.response.DeptListResponse;
import com.kfyty.jiujia.api.response.DoctorDateListResponse;
import com.kfyty.jiujia.api.response.DoctorListResponse;
import com.kfyty.jiujia.api.response.LockResponse;
import com.kfyty.jiujia.api.response.UserListResponse;
import com.kfyty.jiujia.encrypt.AesEncrypt;
import com.kfyty.support.autoconfig.annotation.Async;
import com.kfyty.support.autoconfig.annotation.Autowired;
import com.kfyty.support.autoconfig.annotation.Service;
import com.kfyty.support.utils.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static cn.hutool.core.util.NumberUtil.isInteger;
import static java.lang.Integer.parseInt;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 15:48
 * @email kfyty725@hotmail.com
 */
@Slf4j
@Service
public class LockService {
    private static final Set<UserDoctor> SUCCEED_SET = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private static final Predicate<DeptListResponse.Dept> JIUJIA_TEST =
            e -> e.getDeptName().contains("九价") && !e.getDeptName().contains("二") && !e.getDeptName().contains("三") ||
                    e.getDeptName().toLowerCase().contains("hpv") && !e.getDeptName().contains("二价") && !e.getDeptName().contains("四价");

//    private static final Predicate<DeptListResponse.Dept> JIUJIA_TEST = e -> e.getDeptName().contains("四价");

    public static String tellerInfo;

    @Autowired
    private LockService self;

    @Async
    public void tryLock() {
        while (true) {
            this.findDept();
            log.info("本轮尝试锁定结束, 3 秒后重试！");
            ThreadUtil.sleep(3000);
        }
    }

    private void tryLock(DeptListResponse.Dept dept) {
        DoctorListResponse doctors = this.findDoctors(dept);
        if (doctors == null) {
            log.info("查询医生列表为空: dept={}", dept);
            return;
        }
        List<UserListResponse.UserList> users = this.findUser();
        if (users == null) {
            log.info("查询就诊人列表为空: dept={}", dept);
            return;
        }
        for (DoctorListResponse.Doctor doctor : doctors.getResult()) {
            Supplier<DoctorDateListResponse.DoctorDateResult> doctorDates = () -> this.findDoctorDates(dept.getDay(), doctors.getLockPass(), doctor);
            for (UserListResponse.UserList user : users) {
                boolean success = this.notifyUser(dept.getDay(), doctors.getLockPass(), user, doctor, doctorDates);
                if (success) {
                    break;
                }
            }
        }
    }

    private List<UserListResponse.UserList> findUser() {
        try {
            String userPhone = JSONObject.parseObject(AesEncrypt.decrypt(tellerInfo)).getString("user_phone");
            UserListResponse response = new UserListApi().setHeader(tellerInfo).setUserPhone(userPhone).exchange();
            return CommonUtil.empty(response.getData()) ? null : response.getData();
        } catch (Exception e) {
            log.error("查询就诊人列表失败: {}", e.getMessage(), e);
            return null;
        }
    }

    private void findDept() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            try {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                DeptListApi api = new DeptListApi().setHeader(tellerInfo).setDay(dateFormat.format(calendar.getTime()));
                DeptListResponse response = api.exchange();
                if (CommonUtil.empty(response.getResult())) {
                    log.info("查询部门为空！");
                    break;
                }
                Optional<DeptListResponse.Dept> any = response.getResult().stream().filter(JIUJIA_TEST).findAny();
                if (!any.isPresent()) {
                    log.info("目标日期没有符合条件的部门: date={}", api.getDay());
                    continue;
                }
                any.get().setDay(api.getDay());
                log.info("查询到符合条件的部门: {}", any.get());
                this.tryLock(any.get());
            } catch (Exception e) {
                if (e.getMessage().contains("没有可预约科室")) {
                    log.info("查询部门为空！");
                    break;
                }
                if (e.getMessage().contains("stop_system")) {
                    log.info("系统维护中，十分钟后重试...");
                    ThreadUtil.sleep(10 * 60 * 1000);
                    continue;
                }
                log.error("处理目标日期部门失败: date={}, msg={}", dateFormat.format(calendar.getTime()), e.getMessage(), e);
            }
        }
    }

    private DoctorListResponse findDoctors(DeptListResponse.Dept dept) {
        try {
            DoctorListApi api = new DoctorListApi().setHeader(tellerInfo).setDay(dept.getDay()).setDeptId(dept.getDeptId());
            DoctorListResponse response = api.exchange();
            if (CommonUtil.empty(response.getResult())) {
                return null;
            }
            return response;
        } catch (Exception e) {
            if (e.getMessage().contains("科室没有相应的值班医生")) {
                return null;
            }
            log.error("查询医生列表失败: {}", e.getMessage(), e);
            return null;
        }
    }

    private DoctorDateListResponse.DoctorDateResult findDoctorDates(String day, String lockPass, DoctorListResponse.Doctor doctor) {
        try {
            DoctorDateListApi api = new DoctorDateListApi()
                    .setHeader(tellerInfo)
                    .setAsRowid(doctor.getAsRowid())
                    .setVisitID(doctor.getVisitID())
                    .setDay(day)
                    .setLockPass(lockPass);
            DoctorDateListResponse response = api.exchange();
            if (CommonUtil.empty(response.getResult().getList())) {
                return null;
            }
            List<DoctorDateListResponse.ResultList> collect = response.getResult().getList().stream().filter(e -> isInteger(e.getRegCount()) && parseInt(e.getRegCount()) > 0).collect(Collectors.toList());
            response.getResult().setList(collect);
            return CommonUtil.empty(response.getResult().getList()) ? null : response.getResult();
        } catch (Exception e) {
            log.error("查询医生值班列表失败: date={}, msg={}", day, e.getMessage(), e);
            return null;
        }
    }

    private boolean notifyUser(String day, String lockPass, UserListResponse.UserList user, DoctorListResponse.Doctor doctor, Supplier<DoctorDateListResponse.DoctorDateResult> doctorDates) {
        while (true) {
            DoctorDateListResponse.DoctorDateResult result = doctorDates.get();
            if (result == null) {
                log.warn("该医生值班号已被预约完毕, date={}", day);
                return false;
            }
            int succeed = 0;
            for (DoctorDateListResponse.ResultList doctorDate : result.getList()) {
                final UserDoctor key = new UserDoctor(user.getPatientId(), doctor.getMarkDesc(), doctorDate.getTimeValue());
                if (SUCCEED_SET.contains(key)) {
                    continue;
                }
                boolean lock = this.doTryLock(day, lockPass, user, doctor, doctorDate);
                if (!lock) {
                    log.error("就诊人: {}, 尝试锁定: {}, day={}, timeValue={}, 失败！", user.getPatientName(), doctor.getMarkDesc(), day, doctorDate.getTimeValue());
                    continue;
                }
                succeed++;
                SUCCEED_SET.add(key);
                log.info("就诊人: {}, 尝试锁定: {}, day={}, timeValue={}, 成功！", user.getPatientName(), doctor.getMarkDesc(), day, doctorDate.getTimeValue());
                this.self.sendMessage(CommonUtil.format("就诊人: {}, 尝试锁定: {}, day={}, timeValue={}, now={}, 成功！", user.getPatientName(), doctor.getMarkDesc(), day, doctorDate.getTimeValue(), DateUtil.now()));
                if (result.getList().size() > 20 && succeed > result.getList().size() / 2) {        // 暂时锁定一半的预约，毕竟我的良心大大的
                    return true;
                }
            }
        }
    }

    private boolean doTryLock(String day, String lockPass, UserListResponse.UserList user, DoctorListResponse.Doctor doctor, DoctorDateListResponse.ResultList doctorDate) {
        try {
            LockApi api = new LockApi()
                    .setHeader(tellerInfo)
                    .setAsRowid(doctor.getAsRowid())
                    .setVisitID(doctor.getVisitID())
                    .setDay(day)
                    .setLockPass(lockPass)
                    .setSerNum(doctorDate.getSerNum())
                    .setPatientID(user.getPatientId());
            LockResponse response = api.exchange();
            return response.getResult() != null &&
                    response.getResult().getSuccess() != null &&
                    response.getResult().getSuccess();
        } catch (Exception e) {
            log.error("进行锁定失败: day={}, timeValue={}, user={}, msg={}", day, doctorDate.getTimeValue(), user.getPatientName(), e.getMessage(), e);
            return false;
        }
    }

    @Async
    public void sendMessage(String msg) {
        MonitorMessage message = MonitorMessage.of(msg);
        try {
            String response = HttpRequest
                    .post("https://open.feishu.cn/open-apis/bot/v2/hook/b2e21f4a-1e38-41c0-ad32-6885490434d9")
                    .header("Content-Type", "application/json; charset=utf-8")
                    .body(JSON.toJSONString(message), "utf-8")
                    .execute()
                    .body();
            log.info("通知结果: {}", response);
        } catch (Exception e) {
            log.error("通知失败 !", e);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MonitorMessage {
        @JSONField(name = "msg_type")
        private String msgType;

        private MessageContent content;

        public static MonitorMessage of(String message) {
            return new MonitorMessage("text", new MessageContent(message));
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MessageContent {
            private String text;
        }
    }
}
