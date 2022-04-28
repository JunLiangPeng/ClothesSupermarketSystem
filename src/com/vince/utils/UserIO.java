package com.vince.utils;

import com.vince.bean.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserIO {
    private static List<User> users = new ArrayList<>();
    private static final String USER_FILE = "user.obj";
    //写入用户列表
    public boolean writeUsers() throws BusinessException{
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(USER_FILE));
            o.writeObject(users);
            o.close();
            return true;
        }catch (IOException e){
            throw  new BusinessException("io.write.error");
        }

    }

    public boolean readUsers () throws BusinessException{
        try {
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(USER_FILE));
            List<User> users = (List<User>) oi.readObject();
            this.users = users;
            oi.close();
            return true;
        } catch (IOException|ClassNotFoundException e) {
            throw new BusinessException("io.read.error");
        }
    }

    public void addUser(User user){
        user.setId(users.size()+1);
        users.add(user);
    }

    public User findByUsername(String username,String password){
        for(User user:users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

}
