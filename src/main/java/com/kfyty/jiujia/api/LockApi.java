package com.kfyty.jiujia.api;

import com.kfyty.jiujia.api.response.LockResponse;
import com.kfyty.sdk.api.core.annotation.Parameter;
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
public class LockApi extends AbstractJiujiaApi<LockApi, LockResponse> {
    @Parameter(value = "RegType", defaultValue = "3")
    private String RegType;

    @Parameter("AsRowid")
    private String asRowid;

    @Parameter("VisitID")
    private String visitID;

    @Parameter("Day")
    private String day;

    @Parameter("LockPass")
    private String lockPass;

    @Parameter(value = "EnSerNumList", require = false)
    private String enSerNumList;

    @Parameter("SerNum")
    private String serNum;

    @Parameter("PatientID")
    private String patientID;

    @Override
    public String requestURL() {
        return "http://llyl.mbcloud.com/wx/services/service/theDayRegistered/Thelock.do";
    }

    @Override
    public String method() {
        return "POST";
    }
}
