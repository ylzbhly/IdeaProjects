package com.cubegalaxy.cubestage.mybatisdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cubegalaxy.cubestage.mybatisdemo.entity.User_Place;
import com.cubegalaxy.cubestage.mybatisdemo.mapper.UserPlaceMapper;
import com.cubegalaxy.cubestage.mybatisdemo.service.UserPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class UserPlaceServiceImpl extends ServiceImpl<UserPlaceMapper, User_Place> implements UserPlaceService {
}
