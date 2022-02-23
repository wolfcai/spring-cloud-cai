package com.cai.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cai.user.dao.UserDao;
import com.cai.user.model.entity.User;
import com.cai.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author clz
 * @since 2022-01-28 14:44:26
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

