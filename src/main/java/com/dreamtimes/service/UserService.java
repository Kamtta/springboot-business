package com.dreamtimes.service;

import com.dreamtimes.commons.ServerResponse;
import com.dreamtimes.pojo.User;

public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    ServerResponse login(String username,String password);

    /**
     * 注册
     * @param user
     * @return
     */
    ServerResponse register(User user);

    /**
     * 检查用户名是否有效
     */
    ServerResponse check_valid(String str,String type);
}
