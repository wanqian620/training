package com.whstone.monitor.client.fallback;

import com.whstone.monitor.client.UserClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserClientImpl implements UserClient {

    @Override
    public List<Map> listHosts() {
        return null;
    }
}
