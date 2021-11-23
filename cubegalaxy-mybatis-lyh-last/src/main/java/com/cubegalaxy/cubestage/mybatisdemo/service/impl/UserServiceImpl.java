package com.cubegalaxy.cubestage.mybatisdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cubegalaxy.cubestage.mybatisdemo.entity.Place;
import com.cubegalaxy.cubestage.mybatisdemo.entity.User;
import com.cubegalaxy.cubestage.mybatisdemo.entity.User_Place;
import com.cubegalaxy.cubestage.mybatisdemo.mapper.UserMapper;
import com.cubegalaxy.cubestage.mybatisdemo.service.PlaceService;
import com.cubegalaxy.cubestage.mybatisdemo.service.UserPlaceService;
import com.cubegalaxy.cubestage.mybatisdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户Service实现类
 * @author lyh
 * @version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserPlaceService userPlaceService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 用户信息分页查询
     * @param description 用户介绍
     * @param pageNum 当前页数
     * @param pageSize 每页条数
     * @return page对象
     * @throws Exception 异常
     */
    @Override
    public IPage<User> doPage(String description, Integer pageNum, Integer pageSize) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        if (StringUtils.hasText(description)) {
            queryWrapper.like(User.DESCRIPTION, description);
        }
        IPage<User> userPage = new Page<User>(pageNum, pageSize);
        logger.debug("分页开始,用户介绍：[{}],当前页数：[{}],每页条数：[{}]",description,pageNum,pageSize);
        IPage<User> page = userService.page(userPage, queryWrapper);
        logger.debug("分页结束");
        return page;
    }

    /**
     * 新增用户
     * @param user 用户信息
     * @throws Exception 异常
     */
    @Override
    public void insert(User user) throws Exception{
        user.setCreateDate(new Date());
        logger.debug("往用户表新增用户开始,用户信息为：[{}]",user);
        userService.save(user);
        logger.debug("往用户表新增用户结束");
        //根据旅游景点的名字查找旅游景点
        QueryWrapper<Place> queryWrapper = new QueryWrapper<Place>().eq(Place.NAME, user.getPlaceName());
        Place place = placeService.getOne(queryWrapper);
        //往中间表插入两个id
        User_Place user_place = new User_Place();
        user_place.setUserId(user.getId());
        user_place.setPlaceId(place.getId());
        logger.debug("往中间表插入数据开始,user_id:[{}],place_id:[{}]",user_place.getUserId(),user_place.getPlaceId());
        userPlaceService.save(user_place);
        logger.debug("往中间表插入数据结束");
        //新增之后对应的旅游景点已售出的票数+1
        UpdateWrapper<Place> placeUpdateWrapper = new UpdateWrapper<Place>().set(Place.SOLD_VOTES, place.getSoldVotes() + 1);
        placeUpdateWrapper.eq(Place.ID, place.getId());
        if (place.getVotes() >= place.getSoldVotes()) {
            logger.debug("往旅游景点表中插入数据开始,原始已售出票数为：[{}]",place.getSoldVotes());
            boolean updateReturn = placeService.update(placeUpdateWrapper);
            logger.debug("往旅游景点表中插入数据结束,修改是否成功：[{}]",updateReturn);
        }
    }

    /**
     * 修改用户
     * @param user 用户信息
     * @throws Exception 异常
     */
    @Override
    public void updateUser(User user) throws Exception{
        //判断用户是否有变更旅游景点

        if (userService.getById(user.getId()).getPlaceName() != user.getPlaceName()) {
            logger.debug("用户有变更旅游景点,原始的旅游景点名字为：[{}],修改后的旅游景点名字为：[{}]",userService.getById(user.getId()).getPlaceName(),user.getPlaceName());
            QueryWrapper<Place> queryWrapper = new QueryWrapper<Place>().eq(Place.NAME, user.getPlaceName());
            Integer placeId = placeService.getOne(queryWrapper).getId();
            userService.updateById(user);
            //根据user_id更新place_id
            UpdateWrapper<User_Place> userPlaceUpdateWrapper = new UpdateWrapper<User_Place>().set(User_Place.PLACE_ID, placeId);
            userPlaceUpdateWrapper.eq(User_Place.USER_ID, user.getId());
            logger.debug("更新中间表开始,place_id为：[{}],user_id为：[{}]",placeId,user.getId());
            userPlaceService.update(userPlaceUpdateWrapper);
            logger.debug("更新中间表结束");
            //修改变更后的旅游景点已售出票数+1
            UpdateWrapper<Place> placeUpdateWrapper = new UpdateWrapper<Place>().set(Place.SOLD_VOTES, placeService.getById(placeId).getSoldVotes() + 1);
            placeUpdateWrapper.eq(Place.ID, placeId);
            logger.debug("更新旅游景点表开始");
            placeService.update(placeUpdateWrapper);
            logger.debug("更新旅游景点表结束");
        } else {
            logger.debug("用户没有变更旅游景点");
            logger.debug("更新旅游景点表开始,用户信息为：[{}]",user);
            userService.updateById(user);
            logger.debug("更新旅游景点表结束");
        }
    }

    /**
     * 删除用户
     * @param id 用户id
     * @throws Exception 异常
     */
    @Override
    public void deleteUser(Integer id) throws Exception{
        logger.debug("删除用户表开始,id为：[{}]",id);
        userService.removeById(id);
        logger.debug("删除用户表结束");
        //根据user_id删除中间表的相对应数据
        QueryWrapper<User_Place> user_placeQueryWrapper = new QueryWrapper<>();
        user_placeQueryWrapper.eq(User_Place.USER_ID, id);
        logger.debug("删除中间表数据开始,user_id为：[{}]",id);
        userPlaceService.remove(user_placeQueryWrapper);
        logger.debug("删除中间表数据结束");
    }

    /**
     * 查询所有旅游景点集合
     * @return 旅游景点集合
     * @throws Exception 异常
     */
    @Override
    public List<String> selectAll() throws Exception {
        logger.debug("查询旅游景点开始");
        List<String> placeName = placeService.selectAll();
        logger.debug("查询旅游景点结束,旅游景点名字的集合为：[{}]",placeName);
        return placeName;
    }


}
