package com.whstone.monitor.controller;

import com.whstone.common.constant.AccessRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/monitor/host")
@Api(value = "HostController",tags = "监控相关操作")
public class HostController {

    @GetMapping("/list")
    @ApiOperation("获取监控列表")
    @AccessRequired(hasAnyAuthority = false)
    public List<Map> listHosts(@RequestHeader("token") String token){
        List<Map> list = new ArrayList<>();
        for ( int i=1;i <= 20;i++ ){
            Map map = new HashMap();
            map.put("id",i);
            map.put("name","监控设备" + i);
            map.put("status",true);
            map.put("create_time",new Date());
            list.add(map);
        }
        return list;
    }

    @GetMapping("/findById/{id}")
    @ApiOperation("根据ID获取监控设备信息")
    @AccessRequired(hasAnyAuthority = false)
    public Map findById(@PathVariable("id") Integer id){
        Map map = new HashMap();
        map.put("id",id);
        map.put("name","监控设备" + id);
        map.put("status",true);
        map.put("create_time",new Date());
        return map;
    }

}
