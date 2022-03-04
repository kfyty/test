package com.kfyty.jiujia.api;

import com.kfyty.jiujia.api.response.UserListResponse;
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
public class UserListApi extends AbstractJiujiaApi<UserListApi, UserListResponse> {
    @Parameter(value = "user_phone")
    private String userPhone;

    @Override
    public String requestURL() {
        return "http://llyl.mbcloud.com/wx/services/pation/pation/getPactionListByHis.do";
    }

    @Override
    public String method() {
        return "POST";
    }
}
