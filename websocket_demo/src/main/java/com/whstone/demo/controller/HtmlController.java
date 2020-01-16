package com.whstone.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: wangzh
 * @Date: 2019/12/6
 */
@Controller
public class HtmlController {

    @GetMapping("/websocket")
    public String index() {
        return "index";
    }

    @GetMapping("/websocket1")
    public String index2() {
        return "index2";
    }
}
