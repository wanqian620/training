package com.whstone.auth.domain.entity;

import javax.persistence.*;

@Table(name = "role")
public class RoleDO {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 状态	enable-启用，forbidden-禁用
状态:enable-启用，forbidden-禁用
     */
    @Id
    private String status;

    /**
     * 角色编码：role_admin/role_anon
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取状态	enable-启用，forbidden-禁用
状态:enable-启用，forbidden-禁用
     *
     * @return status - 状态	enable-启用，forbidden-禁用
状态:enable-启用，forbidden-禁用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态	enable-启用，forbidden-禁用
状态:enable-启用，forbidden-禁用
     *
     * @param status 状态	enable-启用，forbidden-禁用
状态:enable-启用，forbidden-禁用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取角色编码：role_admin/role_anon
     *
     * @return code - 角色编码：role_admin/role_anon
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色编码：role_admin/role_anon
     *
     * @param code 角色编码：role_admin/role_anon
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }
}