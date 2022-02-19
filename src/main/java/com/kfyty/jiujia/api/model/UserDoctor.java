package com.kfyty.jiujia.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 18:20
 * @email kfyty725@hotmail.com
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class UserDoctor {
    private String user;
    private String doctor;
    private String timeValue;
}
