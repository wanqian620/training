package com.whstone.common.feign;

import com.whstone.common.feign.fallback.MonitorFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@FeignClient(name="whstone-monitor-focus",path = "/api/monitor",fallback = MonitorFallbackFactory.class)
public interface MonitorClient {

    @RequestMapping(value = "/host/findById/{id}",method = RequestMethod.GET)
    Map findById(@PathVariable("id") Integer id);

}
