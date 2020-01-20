package com.whstone.common.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryVO {

    private Long id;

    private String code;

    private String name;

    private List<ResourceVO> authResources;
}
