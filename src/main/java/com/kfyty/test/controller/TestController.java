package com.kfyty.test.controller;

import com.kfyty.mvc.annotation.Controller;
import com.kfyty.mvc.annotation.GetMapping;
import com.kfyty.mvc.annotation.PathVariable;
import com.kfyty.mvc.annotation.PostMapping;
import com.kfyty.mvc.annotation.PutMapping;
import com.kfyty.mvc.annotation.RequestBody;
import com.kfyty.mvc.annotation.RequestMapping;
import com.kfyty.mvc.annotation.RequestParam;
import com.kfyty.mvc.annotation.ResponseBody;
import com.kfyty.mvc.multipart.MultipartFile;
import com.kfyty.mvc.request.RequestMethod;
import com.kfyty.support.autoconfig.annotation.Autowired;
import com.kfyty.test.dto.DeptDto;
import com.kfyty.test.dto.UserDto;
import com.kfyty.test.exception.BusinessException;
import com.kfyty.test.service.TestService;
import jakarta.validation.constraints.Size;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/5/22 15:02
 * @email kfyty725@hotmail.com
 */
@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestService service;

    @RequestMapping("home")
    public String index() {
        return "/home";
    }

    @ResponseBody
    @RequestMapping("hello")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "hello: " + name;
    }

    @ResponseBody
    @RequestMapping(value = "array", requestMethod = RequestMethod.POST)
    public String[] array(String[] ids) {
        return ids;
    }

    @ResponseBody
    @PostMapping(value = "list")
    public List<String> list(@RequestParam @Size(min = 1) List<String> ids) {
        return ids;
    }

    @ResponseBody
    @GetMapping("map")
    public Map<String, Object> map(Map<String, Object> map) {
        return map;
    }

    @ResponseBody
    @GetMapping("rest/{name}")
    public String rest(@PathVariable String name) {
        return "rest: " + name;
    }

    @ResponseBody
    @PutMapping("user")
    public UserDto userDto(@RequestParam UserDto userDto) {
        return this.service.test(userDto);
    }

    @ResponseBody
    @PutMapping("user-body")
    public UserDto userDtoBody(@RequestBody UserDto userDto) {
        return userDto;
    }

    @ResponseBody
    @PostMapping("user-dept")
    public UserDto userDto(UserDto userDto, @RequestParam DeptDto deptDto) {
        userDto.setDeptId(deptDto.getId());
        return userDto;
    }

    @PostMapping("upload")
    public void upload(String fileName, MultipartFile file) throws Exception {
        file.transferTo(new File("D:\\temp\\upload-test.file"));
    }

    @GetMapping("exception")
    public void exception() {
        throw new BusinessException("redirect:/index");
    }

    @GetMapping("rest-exception")
    public void restException() throws Exception {
        throw new Exception("rest-exception");
    }
}
