package com.kfyty.jiujia.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DoctorDateListResponse extends AbstractResponse<Void> {
    private DoctorDateResult result;

    @Data
    public static class DoctorDateResult {
        @JsonProperty("success")
        private Boolean success;

        @JsonProperty("ErrorMsg")
        private String errorMsg;

        @JsonProperty("AsRowid")
        private String asRowid;

        @JsonProperty("List")
        private List<ResultList> List;
    }

    @Data
    public static class ResultList {
        @JsonProperty("SerNum")
        private String SerNum;

        @JsonProperty("Price")
        private String Price;

        @JsonProperty("RegCount")
        private String RegCount;

        @JsonProperty("TimeValue")
        private String TimeValue;
    }
}
