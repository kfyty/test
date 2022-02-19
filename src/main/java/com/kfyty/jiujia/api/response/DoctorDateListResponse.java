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
public class DoctorDateListResponse extends AbstractResponse<Void> {
    private DoctorDateResult result;

    @Data
    public static class DoctorDateResult {
        private Boolean success;
        private String errorMsg;
        private String asRowid;
        private List<ResultList> List;
    }

    @Data
    public static class ResultList {
        private String SerNum;
        private String Price;
        private String RegCount;
        private String TimeValue;
    }
}
