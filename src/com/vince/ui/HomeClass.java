package com.vince.ui;


import com.vince.bean.Clothes;
import com.vince.service.ClothesService;
import com.vince.service.impl.ClothesServiceImpl;
import com.vince.utils.ConsoleTable;

import java.util.ArrayList;
import java.util.List;

public class HomeClass extends Base{

    public void show(){
        showProducts();
        println("welcome:"+currentuser.getUsername());
        boolean flag = true;
        while(flag){
            println(getString("home.function"));
            println(getString("info.select"));
            String select = input.nextLine();

            switch(select){
                case "1"://1、查询全部订单
                    findList();
                    flag = false;
                    break;
                case "2":// 2、查找订单
                    findOrderById();
                    flag = false;
                    break;
                case "3":// 3、购买
                    buyProducts();
                    flag = false;
                    break;
                case "0"://0 退出
                    flag = false;
                    System.exit(0);
                default:
                    println("input.error");
                    break;

            }
        }
    }

    //购买
    private void buyProducts() {
    }

    //根据id查找订单
    private void findOrderById() {
    }

    //查询全部订单
    private void findList() {

    }

    private void showProducts(){


        ConsoleTable t = new ConsoleTable(8, true);
        t.appendRow();
        t.appendColum("id").appendColum("brand").appendColum("style").appendColum("color").appendColum("size").appendColum("num").appendColum("price").appendColum("description");


        ClothesService clothesService = new ClothesServiceImpl();
        List<Clothes> clothes = clothesService.list();
        // System.out.println(t.toString());

        for(Clothes c:clothes){
            t.appendRow();
            t.appendColum(c.getId()).appendColum(c.getBrand()).appendColum(c.getStyle()).appendColum(c.getColor()).appendColum(c.getSize()).appendColum(c.getNum()).appendColum(c.getPrice()).appendColum(c.getDescription());
        }
        System.out.println(t.toString());
    }

}
