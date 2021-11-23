package com.cubegalaxy.cubestage.mybatisdemo.controller;
/**
 * Copyright (c) 2019-2119,CubeGalaxy.com All rights reserved.
 */
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cubegalaxy.cubestage.mybatisdemo.common.ResultEnum;
import com.cubegalaxy.cubestage.mybatisdemo.entity.Place;
import com.cubegalaxy.cubestage.mybatisdemo.common.Result;
import com.cubegalaxy.cubestage.mybatisdemo.service.PlaceService;
import com.cubegalaxy.cubestage.mybatisdemo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 旅游景点Controller
 * @author lyh
 * @version 1.0
 */
@RestController
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    private static Logger logger = LoggerFactory.getLogger(PlaceController.class);

    /**
     * 分页查询旅游景点
     * @param lowPrice 最低价
     * @param highPrice 最高价
     * @param pageNum 当前页数
     * @param pageSize 每页条输
     * @return 返回了Result的status,message,data
     */
    @GetMapping("/pageList")
    public Result pageList(Double lowPrice, Double highPrice, Integer pageNum, Integer pageSize) {

            if (pageNum == null || pageNum <= 0) {
                pageNum = 1;
            }
            if (pageSize == null || pageSize <= 0) {
                pageSize = 10;
            }
        try {
            logger.debug("正在请求分页,最低票价：[{}] 最高票价：[{}] 当前页数：[{}] 每页条数：[{}]",lowPrice,highPrice,pageNum,pageSize);
            IPage<Place> placeIPage = placeService.doPage(lowPrice,highPrice,pageNum,pageSize);
            return ResultUtil.success(ResultEnum.QUERY_PAGE_SUCCESS.getStatus(),ResultEnum.QUERY_PAGE_SUCCESS.getMessage(),placeIPage);
        } catch (Exception e) {
            logger.error("分页信息获取失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.QUERY_PAGE_FAILD.getStatus(),ResultEnum.QUERY_PAGE_FAILD.getMessage());
        }
    }

    /**
     * 新增旅游景点
     * @param place 旅游景点
     * @return 返回了Result的status,message,data
     */
    @PostMapping("/insert")
    public Result insertPlace(@RequestBody Place place) {
        try {
            placeService.insert(place);
            logger.debug("新增旅游景点成功，旅游景点信息为：[{}]",place);
            return ResultUtil.success(ResultEnum.INSERT_PLACE_SUCCESS.getStatus(),ResultEnum.INSERT_PLACE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.error("新增旅游景点失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.INSERT_PLACE_FAILD.getStatus(),ResultEnum.INSERT_PLACE_FAILD.getMessage());
        }
    }

    /**
     * 修改旅游景点
     * @param place 旅游景点
     * @return 返回了Result的status,message,data
     */
    @PutMapping("/update")
    public Result updatePlace(@RequestBody Place place) {
        try {
            placeService.updateById(place);
            logger.debug("修改旅游景点信息成功，旅游景点信息为：[{}]",place);
            return ResultUtil.success(ResultEnum.UPDATE_PLACE_SUCCESS.getStatus(),ResultEnum.UPDATE_PLACE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.error("修改旅游景点信息失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.UPDATE_PLACE_FAILD.getStatus(),ResultEnum.UPDATE_PLACE_FAILD.getMessage());
        }
    }

    /**
     * 根据id删除旅游景点
     * @param id 旅游景点id
     * @return 返回了Result的status,message,data
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        try {
            placeService.deleteById(id);
            logger.debug("删除旅游景点成功，旅游景点id为：[{}]",id);
            return ResultUtil.success(ResultEnum.DELETE_PLACE_SUCCESS.getStatus(),ResultEnum.DELETE_PLACE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.error("删除旅游景点失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.DELETE_PLACE_FAILD.getStatus(),ResultEnum.DELETE_PLACE_FAILD.getMessage());
        }
    }

    /**
     * 查询单个旅游景点
     * @param id 旅游景点id
     * @return 返回了Result的status,message,data
     */
    @GetMapping("/getPlace/{id}")
    public Result getPlace(@PathVariable("id") Integer id){
        try {
            Place place = placeService.getById(id);
            logger.debug("查询单个旅游景点成功，旅游景点信息为：[{}]",place);
            return ResultUtil.success(ResultEnum.QUERY_PLACE_SUCCESS.getStatus(),ResultEnum.QUERY_PLACE_SUCCESS.getMessage(),place);
        } catch (Exception e) {
            logger.error("查询单个旅游景点失败，错误信息为：",e);
            return ResultUtil.error(ResultEnum.QUERY_PLACE_FAILD.getStatus(),ResultEnum.QUERY_PLACE_FAILD.getMessage());
        }
    }
}
