package com.cai.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.cai.user.model.entity.User;

/**
 * (User)表数据库访问层
 *
 * @author clz
 * @since 2022-01-28 14:44:26
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

