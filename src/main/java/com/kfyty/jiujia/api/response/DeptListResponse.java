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
public class DeptListResponse extends AbstractResponse<Void> {
    private List<Dept> result;

    @Data
    public static class Dept {
        @JsonProperty("DeptId")
        private String DeptId;

        @JsonProperty("DeptName")
        private String DeptName;

        private String day;         // 手动设置
    }
}
