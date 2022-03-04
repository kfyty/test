package com.kfyty.jiujia.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 11:10
 * @email kfyty725@hotmail.com
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WxPayResponse extends AbstractResponse<String> {

    public WxPayResponse(String code, String msg, String data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }
}
