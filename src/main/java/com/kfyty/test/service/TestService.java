package com.kfyty.test.service;

import com.kfyty.test.dto.UserDto;
import jakarta.validation.Valid;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/9/25 17:18
 * @email kfyty725@hotmail.com
 */
public interface TestService {
    UserDto test(@Valid UserDto userDto);
}
