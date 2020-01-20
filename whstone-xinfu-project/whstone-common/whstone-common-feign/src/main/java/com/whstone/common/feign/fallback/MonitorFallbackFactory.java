package com.whstone.common.feign.fallback;

import com.whstone.common.feign.MonitorClient;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MonitorFallbackFactory implements MonitorClient {

    @Override
    public Map findById(Integer id) {
        Map map = new HashMap();
        map.put("id",id);
        map.put("name","");
        map.put("status",false);
        map.put("create_time",new Date());
        return map;
    }
}
