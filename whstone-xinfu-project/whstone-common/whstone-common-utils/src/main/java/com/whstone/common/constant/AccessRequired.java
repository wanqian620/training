package com.whstone.common.constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于校验当前用户的权限
 * 把注解填写在接口入口处
 *  true 校验， false 放行
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessRequired {
	boolean hasAnyAuthority() default true;
}