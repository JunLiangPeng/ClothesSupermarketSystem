package com.vince.test;

import com.vince.bean.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.List;

public class ReadUsers {

    @Test
    public void test1(){
        try {
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream("user.obj"));
            List<User> users = (List<User>) oi.readObject();
            for(User u:users){
                System.out.println(u.getUsername()+"|"+u.getPassword());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
