package com.whstone.user.service;


import com.whstone.common.domain.user.entity.UserDO;
import com.whstone.common.domain.user.vo.RoleQueryVO;
import com.whstone.common.domain.user.vo.UserRoleVO;

import java.util.List;

public interface UserService{

    int updateUserInfo(Long id);

    UserDO findByUsername(String name);

    UserRoleVO findByUserRole(String name);

    RoleQueryVO findByUserRoleAndResource(Long userId);

    List<UserDO> findByUserAll();

    void addUserALLToRedis();

}
