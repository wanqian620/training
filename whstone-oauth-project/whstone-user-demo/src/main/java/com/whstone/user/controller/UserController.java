package com.whstone.user.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/user")
@RestController
@Api(value = "UserController",tags = "用户")
public class UserController {


    @GetMapping("findById/{id}")
    public Map findById(@PathVariable("id") Long id){
        Map map = new HashMap();
        map.put("id",id);
        map.put("userName","admin" + id);
        return map;
    }
}
