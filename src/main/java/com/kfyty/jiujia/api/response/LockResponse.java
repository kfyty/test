package com.kfyty.jiujia.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 11:10
 * @email kfyty725@hotmail.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LockResponse extends AbstractResponse<LockResponse.LockResult> {
    private LockResult result;

    @Data
    public static class LockResult {
        @JsonProperty("code")
        private String code;

        @JsonProperty("SerNumlock")
        private String SerNumlock;

        @JsonProperty("returnCode")
        private String returnCode;

        @JsonProperty("TransCode")
        private String TransCode;

        @JsonProperty("success")
        private Boolean success;

        @JsonProperty("ErrorMsg")
        private String ErrorMsg;

        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("ResultCode")
        private String ResultCode;

        @JsonProperty("resultMsg")
        private String resultMsg;
    }
}
