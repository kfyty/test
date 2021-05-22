package com.kfyty.test;

import com.kfyty.mvc.annotation.Controller;
import com.kfyty.mvc.annotation.RequestMapping;
import com.kfyty.mvc.annotation.RequestParam;
import com.kfyty.mvc.annotation.ResponseBody;

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

    @RequestMapping("home.do")
    public String index() {
        return "/home";
    }

    @ResponseBody
    @RequestMapping("name.do")
    public String name(@RequestParam("name") String name) {
        return "hello: " + name;
    }
}
