package com.whstone.user.service;

import com.whstone.common.domain.user.vo.RoleQueryVO;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色与可用资源
     * @return
     */
    List<RoleQueryVO> findRoleALLResource();

    /**
     * 把所有角色与可用资源存入缓存中
     * @return
     */
    void addRoleALLResourceToRedis();
}
