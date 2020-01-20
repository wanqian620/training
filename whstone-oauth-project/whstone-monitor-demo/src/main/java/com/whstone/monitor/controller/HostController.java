package com.whstone.monitor.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/monitor/host")
@RestController
@Api(value = "HostController",tags = "监控设备")
public class HostController {


    @GetMapping("findByHostId/{id}")
    public Map findById(@PathVariable("id") Long id){
        Map map = new HashMap();
        map.put("id",id);
        map.put("hostName","监控设备" + id);
        return map;
    }
}
