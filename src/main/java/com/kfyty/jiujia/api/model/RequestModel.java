package com.kfyty.jiujia.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 13:59
 * @email kfyty725@hotmail.com
 */
@Data
@Accessors(chain = true)
public class RequestModel {
    private String data;
    private TellerInfo header;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TellerInfo {
        private String tellerInfo;
    }
}
