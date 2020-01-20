package com.whstone.user.controller;

import com.whstone.common.constant.PublicConstant;
import com.whstone.common.constant.RedisConstant;
import com.whstone.common.constant.ResponseErrorEnum;
import com.whstone.common.constant.ResponseSuccessEnum;
import com.whstone.common.domain.user.dto.LoginUserDTO;
import com.whstone.common.domain.user.dto.TokenDTO;
import com.whstone.common.domain.user.entity.UserDO;
import com.whstone.common.domain.user.vo.TokenVO;
import com.whstone.common.domain.user.vo.UserRoleVO;
import com.whstone.common.feign.RedisClient;
import com.whstone.common.utlis.*;
import com.whstone.user.common.exception.ServerException;
import com.whstone.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Api(value = "LoginController",tags = "登录相关操作")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisClient redisClient;

    /**
     *  /api/springcloud/login 登录
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public HttpResponse login(@RequestBody LoginUserDTO loginUserDTO) throws Exception{
        //校验请求参数
        if (StringUtils.isBlank(loginUserDTO.getUsername()))
            throw new ServerException(ResponseErrorEnum.PARAM_BLACK);
        if (StringUtils.isBlank(loginUserDTO.getPassword()))
            throw new ServerException(ResponseErrorEnum.PARAM_BLACK);

        //判断用户名是否存在
        UserDO user = userService.findByUsername(loginUserDTO.getUsername());
        if (user == null)
            throw new ServerException(ResponseErrorEnum.USER_NOT_EXIST);
        //判断用户是否被禁用
        if (null != user.getStatus() && user.getStatus() != 1)
            throw new ServerException(ResponseErrorEnum.USER_ACCOUNT_FORBIDDEN);

        //密码加密
        String password = AESUtil.encode(loginUserDTO.getPassword(), PublicConstant.WHSTONE_PASSWORD);
        //校验成功
        if(password.equals(user.getPassword())){
            try {
                Long time = null;
                if(loginUserDTO.getRememberMe()){
                    time = RedisConstant.REDIS_SEVEN_DAY; //记住我 设置有效时长7天
                }else{
                    time = RedisConstant.REDIS_ONE_DAY;  //有效时长1天
                }
                //把登录用户存放在缓存中，有时间限制
                String token =  UuidUtil.getUUidToToken();
                //存入缓存
                redisClient.setWithTime(RedisConstant.XFEY_LOGIN_TOKEN + token,user.getId()+"",time);

                TokenVO tokenVO = new TokenVO(token);
                return RestResultGenerator.genSuccessResult(tokenVO, ResponseSuccessEnum.LOGIN_SUCCESS);
            } catch (Exception e) {
                logger.error("loginError", e.getMessage());
                throw new ServerException(ResponseErrorEnum.SYSTEM_INNER_ERROR);
            }
        } else { //登录失败
            throw new ServerException(ResponseErrorEnum.USER_PASSWORD_ERROR);
        }
    }

    /**
     * @api {POST} /api/v1/cloud/logout 退出操作
     * @param tokenDTO
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public HttpResponse logout(@RequestBody TokenDTO tokenDTO) throws ServerException{
        //检验请求参数
        if (StringUtils.isBlank(tokenDTO.getToken()))
            throw new ServerException(ResponseErrorEnum.PARAM_BLACK);

        String userId = redisClient.get(RedisConstant.XFEY_LOGIN_TOKEN + tokenDTO.getToken());
        if(StringUtils.isBlank(userId))
            throw new ServerException(ResponseErrorEnum.USER_NOT_EXIST);

        try {
            // 删除用户登录TOKEN
            redisClient.delKey(RedisConstant.XFEY_LOGIN_TOKEN + tokenDTO.getToken());

            return RestResultGenerator.genSuccessResult(true, ResponseSuccessEnum.LOGINOUT_SUCCESS);
        } catch (Exception e) {
            logger.debug("logoutError", e.getMessage());

            throw new ServerException("退出操作失败");
        }
    }

    /**
     * @api {GET} /api/v1/cloud/validate 2.校验token
     * @param token
     * @return
     */
    @GetMapping("/validate")
    @ApiOperation("校验TOKEN")
    public HttpResponse validateUser(@RequestParam("token") String token) throws ServerException{
        //从缓存中取出userId信息
        String userId = redisClient.get(RedisConstant.XFEY_LOGIN_TOKEN + token);
        if(StringUtils.isBlank(userId))
            throw new ServerException(ResponseErrorEnum.AUTH_EXPIRE);

        //查询用户的信息
        String userJson = redisClient.hget(RedisConstant.XFEY_USER_INFO,userId);
        UserRoleVO userRoleVO = null;
        if(StringUtils.isNotBlank(userJson)){
            userRoleVO = JsonUtil.parseObject(userJson,UserRoleVO.class);
        }
        return RestResultGenerator.genSuccessResult(userRoleVO, ResponseSuccessEnum.QUERY_SUCCESS);
    }
}
