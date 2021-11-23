package com.cubegalaxy.cubestage.mybatisdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cubegalaxy.cubestage.mybatisdemo.entity.Place;

import java.util.List;

public interface PlaceService extends IService<Place>{

    IPage<Place> doPage(Double lowPrice, Double highPrice, Integer pageNum, Integer pageSize) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<String> selectAll() throws Exception;

    void insert(Place place) throws Exception;
}
