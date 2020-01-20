package com.whstone.auth.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVO {

    private Long id;

    /**
     * 菜单编号
     */
    private String code;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 资源或菜单路径
     */
    private String url;

    /**
     * GET POST PUT DELETE PATCH
     */
    private String method;

    /**
     * 状态：enable-启用，forbidden-禁用
     */
    private String status;

    /**
     * 父节点ID
     */
    private Long parentId;

}
