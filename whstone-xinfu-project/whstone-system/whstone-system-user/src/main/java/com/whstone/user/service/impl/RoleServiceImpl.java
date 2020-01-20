package com.whstone.user.service.impl;

import com.whstone.common.constant.RedisConstant;
import com.whstone.common.domain.user.vo.RoleQueryVO;
import com.whstone.common.feign.RedisClient;
import com.whstone.common.utlis.JsonUtil;
import com.whstone.user.mapper.RoleMapper;
import com.whstone.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public List<RoleQueryVO> findRoleALLResource() {
        return roleMapper.findRoleALLResource();
    }

    @Override
    public void addRoleALLResourceToRedis() {
        List<RoleQueryVO> list = this.findRoleALLResource();
        if(list != null && list.size() > 0){
            list.stream().forEach(m->{
                //把角色相关的资源存入缓存中
                redisClient.hset(RedisConstant.XFEY_USER_ROLE,String.valueOf(m.getId()), JsonUtil.toJSON(m));
            });
        }
    }
}
