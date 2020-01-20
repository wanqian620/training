package com.whstone.user.controller;

import com.whstone.common.constant.AccessRequired;
import com.whstone.common.constant.ResponseSuccessEnum;
import com.whstone.common.feign.MonitorClient;
import com.whstone.common.utlis.HttpResponse;
import com.whstone.common.utlis.RestResultGenerator;
import com.whstone.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@Api(value = "UserController",tags = "用户管理操作")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MonitorClient monitorClient;

    @GetMapping("/list")
    @AccessRequired()
    public HttpResponse getUserList(@RequestHeader("Authorization") String token){
        return RestResultGenerator.genSuccessResult(userService.findByUserAll(), ResponseSuccessEnum.QUERY_SUCCESS);
    }

    @GetMapping("get/{id}")
    public int updateUser(@PathVariable("id") Long id){
        userService.addUserALLToRedis();
        return 0;
    }

    @GetMapping("/findByMonitorId/{id}")
    public Map findByMonitorInfo(@PathVariable("id") Long id){
        return monitorClient.findById(id.intValue());
    }



    @PostMapping("createUser")
    @AccessRequired()
    public Map createUser(@RequestHeader("Authorization") String token){
        Map map = new HashMap();
        map.put("data",true);
        map.put("status",200);
        map.put("message","创建成功");
        return map;
    }

    @DeleteMapping("delUser")
    @AccessRequired()
    public Map delUser(){
        Map map = new HashMap();
        map.put("data",true);
        map.put("status",200);
        map.put("message","删除成功");
        return map;
    }

//    /**
//     * 密码修改
//     * /api/v1/cloud/modify-password
//     * @param modifyPasswordDTO
//     * @param token
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("modify-password")
//    @ApiOperation("密码修改")
//    public HttpResponse modifyPassword(@RequestBody ModifyPasswordDTO modifyPasswordDTO, @RequestHeader("token") String token) throws Exception{
//        if(modifyPasswordDTO.getOldPassword() == null)
//            throw new ECException(ResponseErrorEnum.PARAM_BLACK);
//        if(modifyPasswordDTO.getNewPassword() == null)
//            throw new ECException(ResponseErrorEnum.PARAM_BLACK);
//        String oldPassword = modifyPasswordDTO.getOldPassword().trim();
//        String newPassword = modifyPasswordDTO.getNewPassword().trim();
//        //判断字符串中是否含有特定空格
//        String reg="^[^\\s]*$";
//        if(!oldPassword.matches(reg))
//            throw new ECException("旧密码中存在空格,请重新填写");
//        if(!newPassword.matches(reg))
//            throw new ECException("新密码中存在空格,请重新填写");
//
//        //从缓存中取出user信息
//        String userJson = redisService.get(RedisConstant.CLOUD_LOGIN_TOKEN + token);
//        if(StringUtils.isNotBlank(userJson)){
//            JSONObject jsonObject = JSONObject.fromObject(userJson);
//            SysUserModel sysUser = (SysUserModel)JSONObject.toBean(jsonObject,SysUserModel.class);
//            sysUser = sysUserService.findById(sysUser.getId());
//            //校验旧密码是否正确
//            String password = AESUtil.decode(sysUser.getPassword(),PublicConstant.WHSTONE_PASSWORD);
//            if(!password.equals(modifyPasswordDTO.getOldPassword())){
//                throw new ECException(ResponseErrorEnum.USER_OLDPASSWORD_ERROR);
//            }
//            sysUser.setPassword(AESUtil.encode(modifyPasswordDTO.getNewPassword(),PublicConstant.WHSTONE_PASSWORD));
//            sysUserService.update(sysUser);
//            //删除token 重新登录
//            redisService.del(RedisConstant.CLOUD_LOGIN_TOKEN + token);
//            return RestResultGenerator.genSuccessResult(true,ResponseSuccessEnum.UPDATE_PASSWORD_SUCCESS);
//        }
//        return RestResultGenerator.genErrorResult(ResponseErrorEnum.MODIFY_PASSWORD_ERROR);
//    }
//
//    /**
//     * 密码重置
//     * /api/v1/cloud/reset-password
//     * @param token
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("reset-password")
//    @ApiOperation("密码重置")
//    public HttpResponse resetPassword(@RequestHeader("token") String token) throws Exception{
//
//        //从缓存中取出user信息
//        String userJson = redisService.get(RedisConstant.CLOUD_LOGIN_TOKEN + token);
//        if(StringUtils.isNotBlank(userJson)){
//            JSONObject jsonObject = JSONObject.fromObject(userJson);
//            SysUserModel sysUser = (SysUserModel)JSONObject.toBean(jsonObject,SysUserModel.class);
//            sysUser.setPassword(AESUtil.encode(PublicConstant.PASSWORD,PublicConstant.WHSTONE_PASSWORD));
//            sysUserService.update(sysUser);
//            return RestResultGenerator.genSuccessResult(true,ResponseSuccessEnum.OPERATER_SUCCESS);
//        }
//        return RestResultGenerator.genErrorResult(ResponseErrorEnum.MODIFY_PASSWORD_ERROR);
//    }
}
