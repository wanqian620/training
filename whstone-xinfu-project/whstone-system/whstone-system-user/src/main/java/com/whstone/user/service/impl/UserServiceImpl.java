package com.whstone.user.service.impl;

import com.whstone.common.constant.PublicConstant;
import com.whstone.common.constant.RedisConstant;
import com.whstone.common.domain.user.entity.UserDO;
import com.whstone.common.domain.user.vo.RoleQueryVO;
import com.whstone.common.domain.user.vo.UserRoleVO;
import com.whstone.common.feign.RedisClient;
import com.whstone.common.utlis.AESUtil;
import com.whstone.common.utlis.JsonUtil;
import com.whstone.user.mapper.UserMapper;
import com.whstone.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisClient redisClient;

    @Override
    public int updateUserInfo(Long id) {
        UserDO user = UserDO.builder()
                .id(id)
                .updateTime(new Date())
                .password(AESUtil.encode( "admin", PublicConstant.WHSTONE_PASSWORD))
                .build();

        return userMapper.updateByPrimaryKeySelective(user);
    }


    @Override
    public UserDO findByUsername(String name) {
        return userMapper.findByUsername(name);
    }

    @Override
    public RoleQueryVO findByUserRoleAndResource(Long userId) {
        return userMapper.findByUserRoleAndResource(userId);
    }

    @Override
    public UserRoleVO findByUserRole(String name) {
        return userMapper.findByUserRole(name);
    }

    @Override
    public List<UserDO> findByUserAll() {
        return userMapper.selectAll();
    }

    @Override
    public void addUserALLToRedis() {
        List<UserRoleVO> list = userMapper.findUserAllAndRole();
        if(list != null && list.size() > 0){
            list.stream().forEach(m->{
                //存储缓存中
                redisClient.hset(RedisConstant.XFEY_USER_INFO,String.valueOf(m.getId()), JsonUtil.toJSON(m));
            });
        }
    }
}
