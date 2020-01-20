package com.whstone.user.mapper;

import com.whstone.common.domain.user.entity.UserDO;
import com.whstone.common.domain.user.vo.RoleQueryVO;
import com.whstone.common.domain.user.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<UserDO> {

    UserDO findByUsername(@Param("name") String name);

    RoleQueryVO findByUserRoleAndResource(@Param("userId") Long userId);

    UserRoleVO findByUserRole(@Param("name") String name);

    List<UserRoleVO> findUserAllAndRole();
}