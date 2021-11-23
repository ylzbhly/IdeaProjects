package com.cubegalaxy.cubestage.mybatisdemo.controller;
/**
 * Copyright (c) 2019-2119,CubeGalaxy.com All rights reserved.
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cubegalaxy.cubestage.mybatisdemo.common.ResultEnum;
import com.cubegalaxy.cubestage.mybatisdemo.common.Result;
import com.cubegalaxy.cubestage.mybatisdemo.entity.User;
import com.cubegalaxy.cubestage.mybatisdemo.service.UserService;
import com.cubegalaxy.cubestage.mybatisdemo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 * @author lyh
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 分页查询用户信息
     * @param description 用户介绍
     * @param pageNum 当前页数
     * @param pageSize 每页条数
     * @return 返回了Result的status,message,data
     */
    @GetMapping("/pageList")
    public Result pageList(String description, Integer pageNum, Integer pageSize) {
            if (pageNum == null || pageNum <= 0) {
                pageNum = 1;
            }
            if (pageSize == null || pageSize <= 0) {
                pageSize = 10;
            }
        try {
            logger.debug("正在请求分页,用户描述：[{}] 当前页数：[{}] 每页条数：[{}]",description,pageNum,pageSize);
            IPage<User> iPage = userService.doPage(description, pageNum, pageSize);
            return ResultUtil.success(ResultEnum.QUERY_PAGE_SUCCESS.getStatus(), ResultEnum.QUERY_PAGE_SUCCESS.getMessage(), iPage);
        } catch (Exception e) {
            logger.error("生成分页信息失败，信息为：",e);
            return ResultUtil.error(ResultEnum.QUERY_PAGE_FAILD.getStatus(), ResultEnum.QUERY_PAGE_FAILD.getMessage());
        }

    }

    /**
     * 新增用户
     * @param user 用户信息
     * @return 返回了Result的status,message,data
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody User user) {
        try {
            userService.insert(user);
            logger.debug("新增用户成功，用户信息：[{}]",user);
            return ResultUtil.success(ResultEnum.INSERT_USER_SUCCESS.getStatus(), ResultEnum.INSERT_USER_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.error("新增用户失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.INSERT_USER_FAILD.getStatus(), ResultEnum.INSERT_USER_FAILD.getMessage());
        }
    }

    /**
     * 修改用户
     * @param user 用户信息
     * @return 返回了Result的status,message,data
     */
    @PutMapping("/update")
    public Result updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            logger.debug("修改用户信息成功，用户信息：[{}]",user);
            return ResultUtil.success(ResultEnum.UPDATE_USER_SUCCESS.getStatus(), ResultEnum.UPDATE_USER_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.error("修改用户信息失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.UPDATE_USER_FAILD.getStatus(), ResultEnum.UPDATE_USER_FAILD.getMessage());
        }
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 返回了Result的status,message,data
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteUser(@PathVariable("id") Integer id) {
        try {
            userService.deleteUser(id);
            logger.debug("删除用户成功，用户id为：[{}]",id);
            return ResultUtil.success(ResultEnum.DELETE_USER_SUCCESS.getStatus(), ResultEnum.DELETE_USER_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.error("删除用户失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.DELETE_USER_FAILD.getStatus(), ResultEnum.DELETE_USER_FAILD.getMessage());
        }
    }

    /**
     * 查询单个用户
     * @param id 用户id
     * @return 返回了Result的status,message,data
     */
    @GetMapping("/getUser/{id}")
    public Result getUser(@PathVariable("id") Integer id){
        try {
            User user = userService.getById(id);
            logger.debug("查询单个用户成功，用户信息为：[{}]",user);
            return ResultUtil.success(ResultEnum.QUERY_USER_SUCCESS.getStatus(),ResultEnum.QUERY_USER_SUCCESS.getMessage(),user);
        } catch (Exception e) {
            logger.error("查询单个用户失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.QUERY_USER_FAILD.getStatus(),ResultEnum.QUERY_USER_FAILD.getMessage());
        }
    }

    /**
     * 查询所有的旅游景点集合
     * @return 返回了Result的status,message,data
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        try {
            List<String> placeName = userService.selectAll();
            logger.debug("查询所有旅游景点成功,旅游景点的集合为：[{}]",placeName);
            return ResultUtil.success(ResultEnum.QUERY_PLACE_SUCCESS.getStatus(),ResultEnum.QUERY_PLACE_SUCCESS.getMessage(),placeName);
        } catch (Exception e) {
            logger.error("查询旅游景点失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.QUERY_PLACE_FAILD.getStatus(),ResultEnum.QUERY_PLACE_FAILD.getMessage());
        }
    }

}
