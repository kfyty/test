package com.kfyty.jiujia.api.response;

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
        private String code;
        private String SerNumlock;
        private String returnCode;
        private String TransCode;
        private Boolean success;
        private String ErrorMsg;
        private String resultCode;
        private String ResultCode;
        private String resultMsg;
    }
}
