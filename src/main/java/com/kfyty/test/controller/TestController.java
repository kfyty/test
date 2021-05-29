package com.kfyty.test.controller;

import com.kfyty.mvc.annotation.Controller;
import com.kfyty.mvc.annotation.PathVariable;
import com.kfyty.mvc.annotation.RequestMapping;
import com.kfyty.mvc.annotation.RequestParam;
import com.kfyty.mvc.annotation.ResponseBody;

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

    @RequestMapping("home")
    public String index() {
        return "/home";
    }

    @ResponseBody
    @RequestMapping("hello")
    public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
        return "hello: " + name;
    }

    @ResponseBody
    @RequestMapping("map")
    public Map<String, Object> map(Map<String, Object> map) {
        return map;
    }

    @ResponseBody
    @RequestMapping("rest/{name}")
    public String rest(@PathVariable("name") String name) {
        return "rest: " + name;
    }
}
