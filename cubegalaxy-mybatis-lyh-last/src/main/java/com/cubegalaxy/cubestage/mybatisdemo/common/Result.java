package com.cubegalaxy.cubestage.mybatisdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的返回结果
 * @author lyh
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer status;
    private String message;
    private Object data;

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
