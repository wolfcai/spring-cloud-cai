package com.cai.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (User)表实体类
 *
 * @author clz
 * @since 2022-01-28 14:44:26
 */
@SuppressWarnings("serial")
@Data
@TableName("t_user")
public class User {


    @TableId(type = IdType.AUTO)
    private Integer id;



    private String name;

    }

