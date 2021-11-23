package com.cubegalaxy.cubestage.mybatisdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("cubestage_user")
public class User {
    public static final String ID="id";
    public static final String USER_NAME="user_name";
    public static final String PASSWORD="password";
    public static final String TELEPHONE="telephone";
    public static final String SEX="sex";
    public static final String AGE="age";
    public static final String EMAIL="email";
    public static final String CREATE_DATE="create_date";
    public static final String DESCRIPTION="description";
    public static final String PLACE_NAME="place_name";

    @TableId(value = "id", type = IdType.AUTO)
    @TableField("id")
    private Integer id;
    @TableField("user_name")
    private String userName;
    @TableField("password")
    private String passWord;
    @TableField("telephone")
    private String telephone;
    @TableField("sex")
    private String sex;
    @TableField("age")
    private Integer age;
    @TableField("email")
    private String email;
    //用户信息的创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @TableField("create_date")
    private Date createDate;
    //用户的相关描述
    @TableField("description")
    private String description;
    //旅游景点的名字
    @TableField("place_name")
    private String placeName;
}
