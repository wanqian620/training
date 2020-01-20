package com.whstone.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/list")
    public List<Map> listHosts(){
        List<Map> list = new ArrayList<>();
        for ( int i=1;i <= 20;i++ ){
            Map map = new HashMap();
            map.put("id",i);
            map.put("name","user" + i);
            map.put("status",true);
            map.put("create_time",new Date());
            list.add(map);
        }
        return list;
    }

}
