package com.kfyty.test.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/6/4 10:52
 * @email kfyty725@hotmail.com
 */
@Data
public class UserDTO {
    @NotNull
    private Integer id;
    private Integer deptId;
}
