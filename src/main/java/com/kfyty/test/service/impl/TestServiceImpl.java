package com.kfyty.test.service.impl;

import com.kfyty.core.autoconfig.annotation.Service;
import com.kfyty.test.dto.UserDTO;
import com.kfyty.test.service.TestService;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/9/25 17:19
 * @email kfyty725@hotmail.com
 */
@Service
public class TestServiceImpl implements TestService {

    public UserDTO test(UserDTO userDto) {
        return userDto;
    }
}
