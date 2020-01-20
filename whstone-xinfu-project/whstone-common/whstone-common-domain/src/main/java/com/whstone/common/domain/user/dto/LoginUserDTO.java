package com.whstone.common.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: powerchen
 * @Date: 2019/12/10 16:38
 * @Description 用于接收前端发送过来的用户登录信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO implements Serializable {

    private String username;

    private String password;

    private Boolean rememberMe = false;

}
