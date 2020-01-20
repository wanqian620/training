package com.whstone.inspection.controller;

import com.whstone.common.constant.AccessRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/inspection/host")
@Api(value = "HostController",tags = "巡检相关操作")
public class HostController {

    @GetMapping("/list")
    @ApiOperation("获取巡检设备列表")
    @AccessRequired(hasAnyAuthority = false)
    public List<Map> listHosts(@RequestHeader("token") String token){
        List<Map> list = new ArrayList<>();
        for ( int i=1;i <= 20;i++ ){
            Map map = new HashMap();
            map.put("id",i);
            map.put("name","巡检设备" + i);
            map.put("status",true);
            map.put("create_time",new Date());
            list.add(map);
        }
        return list;
    }

}
