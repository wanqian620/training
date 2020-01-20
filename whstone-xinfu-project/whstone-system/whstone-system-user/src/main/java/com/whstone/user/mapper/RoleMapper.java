package com.whstone.user.mapper;

import com.whstone.common.domain.user.entity.RoleDO;
import com.whstone.common.domain.user.vo.RoleQueryVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<RoleDO> {

    List<RoleQueryVO> findRoleALLResource();
}