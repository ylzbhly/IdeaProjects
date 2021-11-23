package com.cubegalaxy.cubestage.mybatisdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cubegalaxy.cubestage.mybatisdemo.entity.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper extends BaseMapper<Place> {

    List<String> selectAll();

}
