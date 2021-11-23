package com.cubegalaxy.cubestage.mybatisdemo.common;

/**
 * 返回结果的枚举
 * @author lyh
 * @version 1.0
 */
public enum ResultEnum {
    QUERY_PAGE_SUCCESS(0,"分页查询成功"),
    QUERY_PAGE_FAILD(1,"分页查询失败"),
    QUERY_USER_SUCCESS(0,"查询用户成功"),
    QUERY_USER_FAILD(1,"查询用户失败"),
    QUERY_PLACE_SUCCESS(0,"查询旅游景点成功"),
    QUERY_PLACE_FAILD(1,"查询旅游景点失败"),

    INSERT_USER_SUCCESS(0,"新增用户成功"),
    INSERT_USER_FAILD(1,"新增用户失败"),
    INSERT_PLACE_SUCCESS(0,"新增旅游景点成功"),
    INSERT_PLACE_FAILD(1,"新增旅游景点失败"),

    UPDATE_USER_SUCCESS(0,"修改用户信息成功"),
    UPDATE_USER_FAILD(1,"修改用户信息失败"),
    UPDATE_PLACE_SUCCESS(0,"修改旅游景点成功"),
    UPDATE_PLACE_FAILD(1,"修改旅游景点失败"),

    DELETE_USER_SUCCESS(0,"删除用户成功"),
    DELETE_USER_FAILD(1,"删除用户失败"),
    DELETE_PLACE_SUCCESS(0,"删除旅游景点成功"),
    DELETE_PLACE_FAILD(1,"删除旅游景点失败"),
    ;
    private Integer status;
    private String message;

    ResultEnum(Integer status,String message) {
        this.status=status;
        this.message=message;
    }

    public Integer getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }


}
