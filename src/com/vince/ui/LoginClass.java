package com.vince.ui;

import com.vince.bean.User;
import com.vince.service.UserService;
import com.vince.service.impl.UserServiceImpl;
import com.vince.utils.BusinessException;

public class LoginClass extends Base{
    public void login(){
        println(getString("input.username"));
        String username = input.nextLine();
        println(getString("input.password"));
        String password = input.nextLine();


        UserService userServiceimpl = new UserServiceImpl();
        User user = userServiceimpl.login(username, password);

        if(user != null){
            print(getString("login.success"));
            currentuser = user;
        } else {
            throw new BusinessException("login.error");
        }


    }
}
