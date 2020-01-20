package com.whstone.auth.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/12/13 15:55
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryVO {

    private Long id;

    private String code;

    private String name;

    private Short status;

    private Date createTime;

    private Date updateTime;

    private List<ResourceVO> authResources;
}
