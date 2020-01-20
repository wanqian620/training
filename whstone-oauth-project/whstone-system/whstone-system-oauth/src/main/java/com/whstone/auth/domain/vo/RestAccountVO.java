package com.whstone.auth.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tomsun28
 * @date 11:55 2018/3/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestAccountVO implements Serializable {

    private static final long serialVersionUID = -895875540581785581L;

    /**
     * 令牌id
     */
    private String tokenId;
    /**
     * 客户标识（用户名、账号）
     */
    private String username;
    /**
     * 访问主张-角色(JWT令牌此项有值)
     */
    private String roles;
    /**
     * 访问主张-资源(JWT令牌此项有值)
     */
    private String perms;

}
