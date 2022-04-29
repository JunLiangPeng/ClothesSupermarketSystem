package com.vince.ui;

import com.vince.bean.User;
import com.vince.framework.BeanFactory;
import com.vince.service.UserService;
import com.vince.service.impl.UserServiceImpl;
import com.vince.utils.BusinessException;

public class LoginClass extends Base{

    private UserService userServiceimpl;

    public LoginClass(){
        userServiceimpl = (UserService) beanFactory.getBean("userService");
    }

    public void login(){
        println(getString("input.username"));
        String username = input.nextLine();
        println(getString("input.password"));
        String password = input.nextLine();
        User user = userServiceimpl.login(username, password);

        if(user != null){
            print(getString("login.success"));
            currentuser = user;
        } else {
            throw new BusinessException("login.error");
        }


    }
}
