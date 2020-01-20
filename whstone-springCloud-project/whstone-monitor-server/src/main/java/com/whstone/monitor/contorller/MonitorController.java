package com.whstone.monitor.contorller;

import com.whstone.monitor.client.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api//monitor")
@Api(value = "MonitorController")
public class MonitorController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/listUsers")
    @ApiOperation("listUsers")
    public List<Map> listUsers(){
        return userClient.listHosts();
    }


    @GetMapping("/listHosts")
    @ApiOperation("listHosts")
    public List<Map> listHosts(){
        System.out.println("------------ 监控设备2 --------------- ");
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

}
