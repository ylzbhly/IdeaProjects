package com.cubegalaxy.cubestage.mybatisdemo.utils;
/**
 * Copyright (c) 2019-2119,CubeGalaxy.com All rights reserved.
 */

import com.cubegalaxy.cubestage.mybatisdemo.common.Result;

/**
 * 返回工具类
 * @author lyh
 * @version 1.0
 */
public class ResultUtil {
    public static Result success(Integer status, String message, Object data) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result success(Integer status, String message) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        return result;
    }

    public static Result error(Integer status, String message, Object data) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result error(Integer status, String message) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        return result;
    }
}
