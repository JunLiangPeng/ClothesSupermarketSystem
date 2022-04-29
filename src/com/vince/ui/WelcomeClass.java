package com.vince.ui;

import com.vince.service.UserService;
import com.vince.service.impl.UserServiceImpl;
import com.vince.utils.BusinessException;
import com.vince.utils.UserIO;

public class WelcomeClass extends Base {
    //
    public void start(){

        UserIO userIO = new UserIO();
        userIO.readUsers();

        println(getString("info.welcome"));
        boolean flag = true;
        while(flag){
            println(getString("input.login.reg"));
            String s = input.nextLine();
            switch (s){
                case "1":
                    println("登录");
                    try {
                        new LoginClass().login();
                        flag = false;
                    } catch (BusinessException e){
                        throw new BusinessException("login.error");
                    }
                    break;
                case "2":
                    try {
                        new RegisterClass().register();
                        println(getString("reg.success"));
                        flag = true;
                    }catch (BusinessException e){
                        println(getString("reg.error"));
                    }

                    println("注册");
                    break;
                default:
                    println(getString("input.error"));
                    break;
            }
        }
        HomeClass homeClass = new HomeClass();
        homeClass.show();
    }
}
