package com.whstone.common.constant;

public enum ResponseSuccessEnum {

    LOGIN_SUCCESS("登陆成功", 200),
    LOGINOUT_SUCCESS("登出成功", 200),
    QUERY_SUCCESS("查询成功", 200),
    QUERY_SUCCESS_NODATA("查无数据", 200),
    CREATE_SUCCESS("创建成功", 200),
    UPDATE_SUCCESS("修改成功", 200),
    DELETE_SUCCESS("删除成功", 200),
    ACTIVE_SUCCESS("激活成功", 200),
    REFRESH_SUCCESS("刷新成功", 200),
    SEND_SUCCESS("推送成功", 200),
    ADD_SUCCESS("添加成功", 200);

    public String name;
    public Integer code;

    ResponseSuccessEnum(String name, Integer code) {
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
