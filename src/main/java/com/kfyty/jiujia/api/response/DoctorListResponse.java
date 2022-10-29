package com.kfyty.jiujia.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kfyty.core.utils.CommonUtil;
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
    @JsonProperty("LockPass")
    private String LockPass;

    private List<Doctor> result;

    @Data
    public static class Doctor {
        @JsonProperty("HBTime")
        private String HBTime;

        @JsonProperty("MarkDesc")
        private String MarkDesc;

        @JsonProperty("DepName")
        private String DepName;

        @JsonProperty("DeptName")
        private String DeptName;

        @JsonProperty("MarkId")
        private String MarkId;

        @JsonProperty("VisitID")
        private String VisitID;

        @JsonProperty("SerContr")
        private String SerContr;

        @JsonProperty("Price")
        private String Price;

        @JsonProperty("RegCount")
        private String RegCount;

        @JsonProperty("AsRowid")
        private String AsRowid;

        @JsonProperty("SessionType")
        private String SessionType;

        @JsonProperty("BegTime")
        private String BegTime;

        @JsonProperty("EndTime")
        private String EndTime;

        public String getDoctor() {
            return this.MarkDesc + "/" + this.getDeptName();
        }

        public String getDeptName() {
            return CommonUtil.notEmpty(this.DepName) ? this.DepName : this.DeptName;
        }
    }
}
