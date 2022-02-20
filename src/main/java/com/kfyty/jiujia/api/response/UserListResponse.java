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
public class UserListResponse extends AbstractResponse<List<UserListResponse.UserList>> {

    @Data
    public static class UserList {
        private String id;
        private String pid;
        private String userId;
        private String userPhone;
        private String profession;
        private String hospitalImgurl;
        private String outpatientCode;
        private String address;
        private String cardNo;
        private String cardName;
        private String patientId;
        private String patientName;
        private String patientNumber;
        private String hospitalName;
    }
}
