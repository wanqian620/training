package com.whstone.common.domain.user.entity;

import javax.persistence.*;

@Table(name = "role_resource")
public class RoleResourceDO {
    @Id
    private Long id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 菜单ID
     */
    @Column(name = "resource_id")
    private Long resourceId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单ID
     *
     * @return resource_id - 菜单ID
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 设置菜单ID
     *
     * @param resourceId 菜单ID
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}