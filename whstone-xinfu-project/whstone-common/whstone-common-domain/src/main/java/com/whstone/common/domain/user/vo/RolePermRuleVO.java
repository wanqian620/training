package com.whstone.common.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermRuleVO implements Serializable {

    private static final long serialVersionUID = 1L;
    // 非角色 匿名拦截器不需要认证
    private static final String ANON_ROLE = "role_anon";

    /**
     * 资源定位符
     */
    private String uri;

    /**
     * GET POST PUT DELETE
     */
    private String method;
    /**
     * 访问资源所需要的角色列表，多个列表用逗号间隔 role_anon,role_admin,role_guest,role_user
     */
    private String needRoles;

    /**
     * description 将url needRoles 转化成shiro可识别的过滤器链：url=rest[角色1:action、角色2:action、角色n:action]
     *
     * @return java.lang.StringBuilder
     */
    public StringBuilder toFilterChain() {

        StringBuilder stringBuilder = new StringBuilder();
        Set<String> setRole = split(this.getNeedRoles());

        // 约定若role_anon角色拥有此uri资源的权限,则此uri资源直接访问不需要认证和权限
        if (!StringUtils.isEmpty(this.getNeedRoles()) && setRole.contains(ANON_ROLE)) {
            stringBuilder.append("anon");
        }
        String perms = this.getNeedRoles();
//        String perms = setRole.stream().map(role -> {
//            String action = RestFilterUtil.getHttpMethodAction(this.getMethod());
//            return role + ":" + action;
//        }).collect(Collectors.joining(","));
        //  其他自定义资源uri需通过rest认证和角色认证
        if (!StringUtils.isEmpty(this.getNeedRoles()) && !setRole.contains(ANON_ROLE)) {
            stringBuilder.append("rest" + "[" + perms + "]");
        }

        return stringBuilder.length() > 0 ? stringBuilder : null;
    }

    public static Set<String> split(String str) {

        Set<String> set = new HashSet<>();
        if (StringUtils.isEmpty(str)) {
            return set;
        }
        set.addAll(CollectionUtils.arrayToList(str.split(",")));
        return set;
    }
}
