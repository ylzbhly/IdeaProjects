package com.cubegalaxy.cubestage.mybatisdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cubestage_place")
public class Place {
    public static final String ID="id";
    public static final String NAME="name";
    public static final String NUMBER="number";
    public static final String PRICE="price";
    public static final String TIME="time";
    public static final String VOTES="votes";
    public static final String SOLD_VOTES="sold_votes";

    @TableId(value = "id",type = IdType.AUTO)
    @TableField("id")
    private Integer id;
    @TableField("name")
    private String name;
    //旅游景点的编号
    @TableField("number")
    private String number;
    //门票的价格
    @TableField("price")
    private Double price;
    //可以游玩的游玩的时间（天）
    @TableField("time")
    private Double time;
    //总票数
    @TableField("votes")
    private Integer votes;
    //已售出的票数
    @TableField("sold_votes")
    private Integer soldVotes;
}
