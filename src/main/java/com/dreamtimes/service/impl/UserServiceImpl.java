package com.dreamtimes.service.impl;

import com.dreamtimes.commons.Const;
import com.dreamtimes.commons.ResponseCode;
import com.dreamtimes.commons.ServerResponse;
import com.dreamtimes.dao.UserMapper;
import com.dreamtimes.pojo.User;
import com.dreamtimes.service.UserService;
import com.dreamtimes.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse login(String username, String password) {
//        非空判断
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }
//        根据用户名和密码进行查询
        User user = userMapper.findByUserNameAndPassWord(username,MD5Utils.getMD5Code(password));
//        返回数据
        if(user == null){
            return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_EXSIT.getStatus(),ResponseCode.USER_NOT_EXSIT.getMsg());
        }
        user.setPassword("");
        return ServerResponse.createServerResponseBySuccess(user);
    }

    @Override
    public ServerResponse register(User user) {
//        非空校验
        if(user == null){
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }
//        判断用户名和邮箱是否存在
        ServerResponse serverResponse = check_valid(user.getUsername(),Const.USERNAME);
        if(!serverResponse.isSuccess()){
            return serverResponse;
        }
//        判断邮箱是否存在
        ServerResponse check_email = check_valid(user.getEmail(),Const.EMAIL);
        if(!check_email.isSuccess()){
            return check_email;
        }
//        对密码进行加密
        user.setPassword(MD5Utils.getMD5Code(user.getPassword()));
//        插入数据
        int insert_result = userMapper.insert(user);
        if (insert_result < 0){
            ServerResponse.createServerResponseByError(ResponseCode.INSERT_USER_ERROR.getStatus(),ResponseCode.INSERT_USER_ERROR.getMsg());
        }
//        返回结果
        return ServerResponse.createServerResponseBySuccess(Const.CHECK_SUCCESS);
    }

    @Override
    public ServerResponse check_valid(String str, String type) {
//        非空校验
        if(StringUtils.isBlank(str) || StringUtils.isBlank(type)){
            ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(),ResponseCode.PARAM_EMPTY.getMsg());
        }
//        判断用户名是否存在
        if(type.equals(Const.USERNAME)){
            int check_user = userMapper.findUserByNameOrByEmail(str,null);
            if(check_user > 0){
                return ServerResponse.createServerResponseByError(ResponseCode.USERNAME_EXSIT.getStatus(),ResponseCode.USERNAME_EXSIT.getMsg());
            }
            return ServerResponse.createServerResponseBySuccess(Const.CHECK_SUCCESS);
        }
//        判断邮箱是否存在
        if(type.equals(Const.EMAIL)){
            int check_email = userMapper.findUserByNameOrByEmail(null,str);
            if(check_email > 0){
                return ServerResponse.createServerResponseByError(ResponseCode.EMAIL_EXSIT.getStatus(),ResponseCode.EMAIL_EXSIT.getMsg());
            }
            return ServerResponse.createServerResponseBySuccess(Const.CHECK_SUCCESS);
        }
//        返回数据
        return ServerResponse.createServerResponseByError(ResponseCode.TYPE_ERROR.getStatus(),ResponseCode.TYPE_ERROR.getMsg());
    }
}
