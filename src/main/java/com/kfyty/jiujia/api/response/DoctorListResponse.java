package com.kfyty.jiujia.api.response;

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
        private String MarkId;
        private String VisitID;
        private String SerContr;
        private String Price;
        private String RegCount;
        private String AsRowid;
    }
}
