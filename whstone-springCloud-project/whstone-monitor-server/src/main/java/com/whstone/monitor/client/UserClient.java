package com.whstone.monitor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(name="whstone-user-server",path = "/user")
public interface UserClient {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    List<Map> listHosts();
}
