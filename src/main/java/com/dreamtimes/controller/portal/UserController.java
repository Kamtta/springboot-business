package com.dreamtimes.controller.portal;

import com.dreamtimes.commons.ServerResponse;
import com.dreamtimes.pojo.User;
import com.dreamtimes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/portal/user/")
public class UserController {

    @Autowired
    UserService userService;
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login.do")
    public ServerResponse login(String username,String password){
        return userService.login(username,password);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do")
    public ServerResponse register(User user){
        return userService.register(user);
    }

    /**
     * 检查用户名是否有效
     */
    @RequestMapping(value = "check_valid/{str}/{type}")
    public ServerResponse check_valid(@PathVariable String str ,
                                      @PathVariable String type){
        return userService.check_valid(str,type);
    }
}
