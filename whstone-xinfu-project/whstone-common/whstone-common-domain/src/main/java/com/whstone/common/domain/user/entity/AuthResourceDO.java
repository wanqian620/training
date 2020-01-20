package com.whstone.common.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResourceDO implements Serializable {

    private Long id;

    private String code;

    private String name;

    private Long parentId;

    private String url;

    private int type;

    private String method;

    private int status;

}