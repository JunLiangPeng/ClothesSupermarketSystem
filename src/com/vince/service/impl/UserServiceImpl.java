package com.vince.service.impl;

import com.vince.bean.User;
import com.vince.service.UserService;
import com.vince.utils.BusinessException;
import com.vince.utils.EmptyUtils;
import com.vince.utils.UserIO;

public class UserServiceImpl implements UserService {
    @Override
    public User register(User user) throws BusinessException {

        UserIO userio = new UserIO();
        userio.addUser(user);
        userio.writeUsers();
        return user;
    }

    @Override
    public User login(String username, String password) throws BusinessException {
        if(EmptyUtils.isEmpty(username)){
            throw new BusinessException("username.notnull");
        }
        if(EmptyUtils.isEmpty(password)){
            throw new BusinessException("password.notnull");
        }
        UserIO userio = new UserIO();
        User user = userio.findByUsername(username, password);
        return user;
    }
}
