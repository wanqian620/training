package com.whstone.auth.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserDO implements Serializable {
    /**
     * 用户主键
     */
    @Id
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 状态:1.启用 2.禁用 3.拉黑
     */
    private Integer status;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;
}