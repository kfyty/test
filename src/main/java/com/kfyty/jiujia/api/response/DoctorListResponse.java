package com.kfyty.jiujia.api.response;

import com.kfyty.support.utils.CommonUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 11:10
 * @email kfyty725@hotmail.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorListResponse extends AbstractResponse<Void> {
    private String LockPass;
    private List<Doctor> result;

    @Data
    public static class Doctor {
        private String HBTime;
        private String MarkDesc;
        private String DepName;
        private String DeptName;
        private String MarkId;
        private String VisitID;
        private String SerContr;
        private String Price;
        private String RegCount;
        private String AsRowid;
        private String SessionType;
        private String BegTime;
        private String EndTime;

        public String getDoctor() {
            return this.MarkDesc + "/" + this.getDeptName();
        }

        public String getDeptName() {
            return CommonUtil.notEmpty(this.DepName) ? this.DepName : this.DeptName;
        }
    }
}
