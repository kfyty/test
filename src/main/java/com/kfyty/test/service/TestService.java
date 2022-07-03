package com.kfyty.test.service;

import com.kfyty.test.dto.UserDTO;
import jakarta.validation.Valid;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/9/25 17:18
 * @email kfyty725@hotmail.com
 */
public interface TestService {
    UserDTO test(@Valid UserDTO userDto);
}
