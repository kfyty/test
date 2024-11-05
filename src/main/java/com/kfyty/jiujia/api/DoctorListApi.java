package com.kfyty.jiujia.api;

import com.kfyty.jiujia.api.response.DoctorListResponse;
import com.kfyty.loveqq.framework.sdk.api.core.annotation.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 11:08
 * @email kfyty725@hotmail.com
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DoctorListApi extends AbstractJiujiaApi<DoctorListApi, DoctorListResponse> {
    @Parameter(value = "RegType", defaultValue = "2")
    private String regType;

    @Parameter(value = "nucleicTest", defaultValue = "lysfybjy0002")
    private String nucleicTest;

    @Parameter("Day")
    private String day;

    @Parameter("DeptId")
    private String deptId;

    @Override
    public String requestPath() {
        return "http://llyl.mbcloud.com/wx/services/service/theDayRegistered/queryDoctor.do";
    }

    @Override
    public String method() {
        return "POST";
    }
}
