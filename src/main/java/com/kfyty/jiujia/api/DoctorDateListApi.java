package com.kfyty.jiujia.api;

import com.kfyty.jiujia.api.response.DoctorDateListResponse;
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
public class DoctorDateListApi extends AbstractJiujiaApi<DoctorDateListApi, DoctorDateListResponse> {
    @Parameter(value = "RegType", defaultValue = "2")
    private String regType;

    @Parameter("AsRowid")
    private String asRowid;

    @Parameter("VisitID")
    private String visitID;

    @Parameter("Day")
    private String day;

    @Parameter("LockPass")
    private String lockPass;

    @Override
    public String requestPath() {
        return "http://llyl.mbcloud.com/wx/services/service/theDayRegistered/queryDoctorDate.do";
    }

    @Override
    public String method() {
        return "POST";
    }
}
