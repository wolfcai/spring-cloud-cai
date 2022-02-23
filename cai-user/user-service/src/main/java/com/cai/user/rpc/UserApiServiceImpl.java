package com.cai.user.rpc;

import com.cai.user.dto.UserDTO;
import com.cai.user.service.UserApiService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Description: TODO
 * @author: clz
 * @email: 464499039@qq.com
 * @Date: 2022/1/28 15:55
 */
@DubboService
public class UserApiServiceImpl implements UserApiService {


    @Override
    public UserDTO getUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("张三");
        return userDTO;
    }
}
