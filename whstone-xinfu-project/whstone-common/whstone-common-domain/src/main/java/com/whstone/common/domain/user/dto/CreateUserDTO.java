package com.whstone.common.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: powerchen
 * @Date: 2019/12/13 17:05
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO implements Serializable {

    private String username;

    private String password;

    private String email;
}
