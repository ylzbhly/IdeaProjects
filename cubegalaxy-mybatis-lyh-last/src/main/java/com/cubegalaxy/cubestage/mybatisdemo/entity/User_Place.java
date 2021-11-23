package com.cubegalaxy.cubestage.mybatisdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cubestage_user_place")
public class User_Place {
    public static final String USER_ID="user_id";
    public static final String PLACE_ID="place_id";

    @TableField("user_id")
    private Integer userId;
    @TableField("place_id")
    private Integer placeId;
}
