package com.vince.test;

import com.vince.bean.Order;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class ReadOrders {
    @Test
    public void readOrders(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("order.obj"));
            List<Order> orders = (List<Order>) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


