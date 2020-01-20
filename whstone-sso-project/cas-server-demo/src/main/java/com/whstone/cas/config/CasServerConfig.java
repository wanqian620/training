package com.whstone.cas.config;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.ArrayList;
import java.util.List;

/**
 ** cas与sercurity整合，需要配置的对象
 *
 * 在cas与security整合中， 首先需要做的是将应用的登录认证入口改为使用CasAuthenticationEntryPoint。
 * 所以首先我们需要配置一个CasAuthenticationEntryPoint对应的bean，
 * 然后指定需要进行登录认证时使用该AuthenticationEntryPoint。
 * 配置CasAuthenticationEntryPoint时需要指定一个ServiceProperties，
 * 该对象主要用来描述service（Cas概念）相关的属性，主要是指定在Cas Server认证成功后将要跳转的地址。
 *
 *
 * CasAuthenticationFilter认真过滤器，负责认证跳转和票据验证 
 *cas与sercurity整合，需要配置的对象
 */
@Configuration
@EnableConfigurationProperties(value= {CasServerProperties.class})
public class CasServerConfig {

	@Autowired
	private CasServerProperties casServerProperties;


	/**
	 * 认证方法的入口，接收一个Authentication对象作为参数
	 * @param provider
	 * @return
	 */
	@Bean
	public AuthenticationManager authenticationManager(CasAuthenticationProvider provider) {
		//当Spring Security默认提供的Provider不能满足需求的时候，
		// 可以通过实现AuthenticationProvider接口来扩展出不同的认证提供者
		List<AuthenticationProvider> providers = new ArrayList<>();
		providers.add(provider);
		//它是AuthenticationManager的一个实现类，实现了authenticate(Authentication authentication)方法，还有一个成员变量
		//List<AuthenticationProvider> providers
		ProviderManager providerManager = new ProviderManager(providers);

		return providerManager;

	}

	/**
	 ** 我们自己应用的配置信息，该对象主要用于构建CasAuthenticationEntryPoint。
	 * @return
	 */
	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		//设置默认的cas登陆后回跳地址
		serviceProperties.setService(casServerProperties.getServiceUrl()+"/login/cas");
		//设置我们应用是否敏感
		serviceProperties.setSendRenew(false);
		//设置是否对未拥有ticket的访问均需要验证
		serviceProperties.setAuthenticateAllArtifacts(true);
		return serviceProperties;
	}
	
	/** 
	 ** CAS认证过滤器，主要实现票据认证和认证成功后的跳转。
	 * @return
	 */
	@Bean
	public CasAuthenticationFilter casAuthenticationFilter(AuthenticationManager auth,ServiceProperties serviceProperties) {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		//给过滤器设置我们应用的基本配置
		casAuthenticationFilter.setServiceProperties(serviceProperties);
		//给过滤器设置认证管理器
		casAuthenticationFilter.setAuthenticationManager(auth);
		//设置过滤器到cas server认证的地址
		casAuthenticationFilter.setFilterProcessesUrl(casServerProperties.getServiceUrl()+"/login/cas");
		//设置是否继续执行其他过滤器，在完成认证前
		casAuthenticationFilter.setContinueChainBeforeSuccessfulAuthentication(false);
        //设置认证成功后的处理handler
		casAuthenticationFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
		return casAuthenticationFilter;
	}
	
	/**
	 ** 认证的入口，即跳转至服务端的cas地址
	 * security框架整合cas认证的入口，也就是security不再走自己的认证入口，而是cas的，该对象就是cas的认证入口
	 * @return
	 * @date
	 */
	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint(ServiceProperties serviceProperties) {
		
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		//security框架整合cas认证的入口，也就是security不再走自己的认证入口，而是cas的，该对象就是cas的认证入口
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties);
		casAuthenticationEntryPoint.setLoginUrl(casServerProperties.getCasServerLoginUrl());
		return casAuthenticationEntryPoint;
	}
	
	
	/**
	 ** 配置TicketValidator在登录认证成功后验证ticket
	 * 该对象就是一个ticket校验器
	 * @return
	 */
	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		//需要设置cas server的前缀，也就是根路径
		return new Cas20ServiceTicketValidator(casServerProperties.getCasServerUrlPrefix());
	}
	
	/**
	 ** 该对象为cas校验对象，TicketValidator、AuthenticationUserDetailService属性必须设置;
     * serviceProperties属性主要应用于ticketValidator用于去cas服务端检验ticket
	 * @param serviceProperties
	 * @param ticketValidator
	 * @return
	 */
	@Bean
	public CasAuthenticationProvider casAuthenticationProvider(ServiceProperties serviceProperties, Cas20ServiceTicketValidator ticketValidator) {
	   
		CasAuthenticationProvider provider = new CasAuthenticationProvider();
	    provider.setKey("CAS_SERVER_6005");
	    provider.setServiceProperties(serviceProperties);
	    provider.setTicketValidator(ticketValidator);
	    provider.setAuthenticationUserDetailsService( s -> new User("admin", "admin", true, true, true, true,
				AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
		return provider;
	}

	@Bean
	public SingleSignOutFilter singleSignOutFilter(){
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setCasServerUrlPrefix(casServerProperties.getCasServerUrlPrefix());
		singleSignOutFilter.setIgnoreInitConfiguration(true);
		return singleSignOutFilter;
	};
	
	@Bean
	public LogoutFilter logoutFilter() {
		String logoutRedirectPath = casServerProperties.getCasServerLogoutUrl()+ "?service=" +casServerProperties.getServiceUrl();
        LogoutFilter logoutFilter = new LogoutFilter(logoutRedirectPath, new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl("/logout");
        return logoutFilter;
        
	}

}
