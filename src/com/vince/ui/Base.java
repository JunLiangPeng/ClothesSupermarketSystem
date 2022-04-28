package com.vince.ui;

import com.vince.bean.User;


import java.util.ResourceBundle;
import java.util.Scanner;

public abstract class Base {
    protected static Scanner input = new Scanner(System.in);
    private static ResourceBundle r = ResourceBundle.getBundle("com.vince.res.r");

    protected static User currentuser;//当前用户对象

    protected static String getString(String key){
        return r.getString(key);
    }

    public static void println(String s){
        System.out.println(s);
    }

    public static void print(String s){
        System.out.print(s);
    }

}
