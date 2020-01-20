package com.whstone.cas.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 ** cas服务的配置信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix="spring.cas")
public class CasServerProperties {

	/** cas服务地址  */
	private String casServerUrlPrefix;
	/** cas登陆地址  */
    private String casServerLoginUrl;
    /** cas登出地址  */
    private String casServerLogoutUrl;
    /** 本应用的地址，该地址需要能被cas服务器访问到  */
    private String serviceUrl;
}
