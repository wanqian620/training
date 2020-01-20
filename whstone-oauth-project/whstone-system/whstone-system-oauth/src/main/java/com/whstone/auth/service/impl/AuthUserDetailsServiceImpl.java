package com.whstone.auth.service.impl;

import com.whstone.auth.domain.entity.UserDO;
import com.whstone.auth.domain.vo.ResourceVO;
import com.whstone.auth.domain.vo.RoleQueryVO;
import com.whstone.auth.mapper.UserMapper;
import com.whstone.auth.service.AuthUserDetailsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service("authUserDetailsService")
public class AuthUserDetailsServiceImpl implements AuthUserDetailsService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        UserDO userDO = this.userMapper.findByUsername(name);
        if(null == userDO){
            throw new UsernameNotFoundException("name not find");
        }
        RoleQueryVO roleQueryVO = this.userMapper.findByUserRoleAndResource(userDO.getId());
        if(null == roleQueryVO){
            throw new UsernameNotFoundException("role and resource not find");
        }
        //存储角色域资源相关信息
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        //角色必须是ROLE_开头，可以在数据库中设置
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleQueryVO.getCode());
        grantedAuthorities.add(grantedAuthority);
        //获取权限
        for (ResourceVO resourceVO : roleQueryVO.getAuthResources()) {
            if(StringUtils.isNotBlank(resourceVO.getUrl())){
                GrantedAuthority authority = new SimpleGrantedAuthority(resourceVO.getUrl());
                grantedAuthorities.add(authority);
            }
        }
        User user = new User(userDO.getUserName(), userDO.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
