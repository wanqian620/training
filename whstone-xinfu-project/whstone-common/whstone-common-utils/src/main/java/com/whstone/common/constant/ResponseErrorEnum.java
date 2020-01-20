package com.whstone.common.constant;

public enum ResponseErrorEnum {
    ILLEGAL_PARAMS("请求参数不合法", 501),
    SERVICE_HANDLE_ERROR("服务器处理错误", 500),
    ALREADY_EXISTS_ERROR("已存在该名称", 430),

    /* 请求错误 controller层处理直接返回 */
    PARAM_INVALID("请求参数无效", 10001),
    PARAM_BLACK("请求参数不能为空", 10002),
    PARAM_TYPE_ERROR("请求参数类型错误", 10003),
    PARAM_NOT_COMPLETE("缺少请求参数", 10004),
    HOSTID_BLANK_ERROR("设备id为空", 10005),


    /* 用户与权限 权限异常由拦截器直接返回 用户异常由用户Controller抛出 */
    USER_NOT_EXIST("用户不存在", 20001),
    USER_PASSWORD_ERROR("用户账号或密码错误", 20002),
    USER_ACCOUNT_FORBIDDEN("用户账号被禁用", 20003),
    USER_IS_EXISTED("用户已存在", 20004),
    AUTH_EXPIRE("需要重新登录", 20005),
    INSUFFICIENT_PERMISSIONS("权限不足", 20006),

    /* 业务错误：与业务相关，补充 */


    /* 系统错误：非业务问题 */
    SYSTEM_INNER_ERROR("系统错误，请查看日志", 40001),
    SYSTEM_LOGINOUT_ERROR("退出缓存清理失败", 40002),

    /* 数据存取错误：数据库操作错误 */
    RESULE_DATA_NONE("数据未找到", 50001),
    DATA_IS_WRONG("数据有误", 50002),
    DATA_ALREADY_EXISTED("数据已存在", 50003),
    DATA_UPDATE_FAIL("数据更新失败", 50004),
    DATA_CREATE_FAIL("数据添加失败", 50005),
    DATA_DELETE_FAIL("数据删除失败", 50006),

    /* WebSocket：相关操作错误提示 */
    WBSOCKET_LOGINOUT_ERROR("当前连接已关闭", 60001);


    public String name;
    public Integer code;

    ResponseErrorEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
