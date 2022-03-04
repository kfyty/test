package com.kfyty.jiujia.api.response;

import com.kfyty.sdk.api.core.support.GenericApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 13:49
 * @email kfyty725@hotmail.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractResponse<T> extends GenericApiResponse<T> {
    private String code;
    private String msg;

    @Override
    public String getDesc() {
        return this.msg;
    }
}
