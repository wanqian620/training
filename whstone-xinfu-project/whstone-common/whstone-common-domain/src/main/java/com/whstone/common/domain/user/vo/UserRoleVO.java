package com.whstone.common.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVO implements Serializable {

    private Long id;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 状态:1.启用 2.禁用 3.拉黑
     */
    private Integer status;

    /**
     * 邮箱地址
     */
    private String userName;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;
}