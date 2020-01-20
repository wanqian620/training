package com.whstone.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;


    @GetMapping("/member")
    public Map user(Principal member) {
        Map map = new HashMap();
        map.put("data",member);
        map.put("status",200);
        map.put("message","操作成功");
        return map;
    }

    @DeleteMapping(value = "/exit")
    public Map revokeToken(String access_token) {
        Map map = new HashMap();
        if (consumerTokenServices.revokeToken(access_token)) {
            map.put("data",true);
            map.put("status",200);
            map.put("message","注销成功");
        } else {
            map.put("data",false);
            map.put("status",500);
            map.put("message","注销失败");
        }
        return map;
    }
}
