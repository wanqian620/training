package com.whstone.auth.mapper;

import com.whstone.auth.domain.entity.UserDO;
import com.whstone.auth.domain.vo.RoleQueryVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<UserDO> {

    UserDO findByUsername(@Param("username") String username);

    RoleQueryVO findByUserRoleAndResource(@Param("userId") Long userId);
}