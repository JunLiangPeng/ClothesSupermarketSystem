package com.vince.ui;

import com.vince.bean.User;
import com.vince.service.UserService;
import com.vince.service.impl.UserServiceImpl;
import com.vince.utils.BusinessException;

public class RegisterClass extends Base{

    private UserService userServiceImpl;

    public RegisterClass (){
        userServiceImpl = (UserService)beanFactory.getBean("userService");
    }

    public void register() throws BusinessException {
        println(getString("input.username"));
        String username = input.nextLine();
        print(getString("input.password"));
        String password = input.nextLine();
        User u = new User(username,password);
        // UserService userServiceImpl = new UserServiceImpl();
        userServiceImpl.register(u);
    }
}
