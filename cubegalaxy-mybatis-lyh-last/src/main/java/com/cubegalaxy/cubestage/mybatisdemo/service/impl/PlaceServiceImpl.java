package com.cubegalaxy.cubestage.mybatisdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cubegalaxy.cubestage.mybatisdemo.entity.Place;
import com.cubegalaxy.cubestage.mybatisdemo.entity.User_Place;
import com.cubegalaxy.cubestage.mybatisdemo.mapper.PlaceMapper;
import com.cubegalaxy.cubestage.mybatisdemo.service.PlaceService;
import com.cubegalaxy.cubestage.mybatisdemo.service.UserPlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 旅游景点Service实现类
 * @author lyh
 * @version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements PlaceService {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserPlaceService userPlaceService;

    private Logger logger = LoggerFactory.getLogger(PlaceServiceImpl.class);

    /**
     *
     * @param lowPrice 最低价
     * @param highPrice 最高价
     * @param pageNum 当前页数
     * @param pageSize 每页条数
     * @return Page 对象
     * @throws Exception 异常
     */
    @Override
    public IPage<Place> doPage(Double lowPrice, Double highPrice, Integer pageNum, Integer pageSize) throws Exception{
        QueryWrapper<Place> placeQueryWrapper = new QueryWrapper<>();
        if (lowPrice != null && lowPrice >= 0) {
            if (highPrice != null && highPrice >= 0) {
                placeQueryWrapper.between(Place.PRICE, lowPrice, highPrice);
            }
        }
        IPage<Place> placePage = new Page<>(pageNum,pageSize);
        logger.debug("分页开始 最低价：[{}],最高价：[{}],当前页数：[{}],每页条数：[{}]",lowPrice,highPrice,pageNum,pageSize);
        IPage<Place> page = placeService.page(placePage, placeQueryWrapper);
        logger.debug("分页结束");
        return page;
    }

    /**
     * 根据id删除旅游景点
     * @param id 旅游景点id
     * @throws Exception 异常
     */
    @Override
    public void deleteById(Integer id) throws Exception{
        logger.debug("删除旅游景点表开始,id为：[{}]",id);
        placeService.removeById(id);
        logger.debug("删除旅游景点表结束");
        //根据place_id删除中间表的数据
        QueryWrapper<User_Place> user_placeQueryWrapper = new QueryWrapper<>();
        user_placeQueryWrapper.eq(User_Place.PLACE_ID,id);
        logger.debug("删除中间表数据开始，旅游景点id为：[{}]",id);
        userPlaceService.remove(user_placeQueryWrapper);
        logger.debug("删除中间表数据结束");
    }

    /**
     * 查询所有旅游景点名称集合
     * @return 旅游景点名称集合
     * @throws Exception 异常
     */
    @Override
    public List<String> selectAll() throws Exception{
        logger.debug("查询所有旅游景点名称开始");
        List<String> placeName = baseMapper.selectAll();
        logger.debug("查询所有旅游景点名称结束,所有旅游景点的集合为：[{}]",placeName);
        return placeName;
    }

    /**
     * 新增旅游景点
     * @param place 旅游景点对象
     * @throws Exception 异常
     */
    @Override
    public void insert(Place place) throws Exception{
        logger.debug("新增旅游景点开始。旅游景点为：[{}]",place);
        place.setSoldVotes(0);
        placeService.save(place);
    }
}
