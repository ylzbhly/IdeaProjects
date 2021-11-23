package com.cubegalaxy.cubestage.mybatisdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cubegalaxy.cubestage.mybatisdemo.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    IPage<User> doPage(String description, Integer pageNum, Integer pageSize) throws Exception;

    void insert(User user) throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(Integer id) throws Exception;

    List<String> selectAll() throws Exception;

}
