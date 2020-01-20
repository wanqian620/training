package com.whstone.user.common.interceptor;


import com.whstone.common.constant.AccessRequired;
import com.whstone.common.constant.RedisConstant;
import com.whstone.common.constant.ResponseErrorEnum;
import com.whstone.common.domain.user.vo.ResourceVO;
import com.whstone.common.domain.user.vo.RoleQueryVO;
import com.whstone.common.domain.user.vo.UserRoleVO;
import com.whstone.common.feign.RedisClient;
import com.whstone.common.utlis.JsonUtil;
import com.whstone.user.common.exception.ServerException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 拦截url中的access_token
 * @author Nob
 * 
 */
public class UserAccessApiInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisClient redisClient;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

	@Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

		String token = request.getHeader("token");
		if(StringUtils.isBlank(token)){
			throw new ServerException(ResponseErrorEnum.AUTH_EXPIRE);
		}
    	if (handler instanceof HandlerMethod) {
	        HandlerMethod handlerMethod = (HandlerMethod) handler;
	        //获得该路径的方法
	        Method method = handlerMethod.getMethod();
	        //取得该方法上的AccessRequired注解
	        AccessRequired annotation = method.getAnnotation(AccessRequired.class);
	        //不加注解或hasAnyAuthority为true则拦截,hasAnyAuthority为false则不拦截
	        if (annotation == null || (annotation != null && annotation.hasAnyAuthority())) {
	        	//根据TOKEN 获取用户ID
				String userId = redisClient.get(RedisConstant.XFEY_LOGIN_TOKEN + token);
				//根据用户ID获取用户信息
				String userJson = redisClient.hget(RedisConstant.XFEY_USER_INFO,userId);
                if(userJson == null) return false;
                UserRoleVO userRoleVO = JsonUtil.parseObject(userJson,UserRoleVO.class);
				String roleResourceJson = redisClient.hget(RedisConstant.XFEY_USER_ROLE,userRoleVO.getRoleId()+"");
                if(roleResourceJson == null){
                    throw new ServerException(ResponseErrorEnum.INSUFFICIENT_PERMISSIONS);
                }
                //权限校验
                this.hasAnyAuthority(roleResourceJson,request);
	        }
    	}
    	return true;
    }

	/**
	 * 判断当前用户是否拥有权限
	 * @param roleResourceJson
	 * @param request
	 * @return
	 * @throws ServerException
	 */
    private boolean hasAnyAuthority(String roleResourceJson,HttpServletRequest request) throws ServerException{
		// 获取访问的url
		String requestUrl = request.getRequestURI();
		//判断是否配置权限
		RoleQueryVO roleQueryVO = JsonUtil.parseObject(roleResourceJson,RoleQueryVO.class);
		List<ResourceVO> authResources = roleQueryVO.getAuthResources();
		if(authResources != null && authResources.size() > 0){
			for(ResourceVO resourceVO : authResources){
				if(resourceVO.getUrl() != null && requestUrl.indexOf(resourceVO.getUrl()) > -1){
					return true;
				}
			}
			throw new ServerException(ResponseErrorEnum.INSUFFICIENT_PERMISSIONS);
		}
		return true;
	}
}
