package com.vince.ui;


import com.vince.bean.Clothes;
import com.vince.bean.Order;
import com.vince.bean.OrderItem;
import com.vince.service.ClothesService;
import com.vince.service.OrderService;
import com.vince.service.impl.ClothesServiceImpl;
import com.vince.service.impl.OrderServiceImpl;
import com.vince.utils.BusinessException;
import com.vince.utils.ConsoleTable;
import com.vince.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeClass extends Base{

    private OrderService orderService = new OrderServiceImpl();
    private ClothesService clothesService = new ClothesServiceImpl();

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
                    try {
                        buyProducts();
                        flag = false;
                    } catch (BusinessException e){
                        println(e.getMessage());
                    }

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

    //购买商品
    //购买
    private void buyProducts() throws BusinessException {
        //生成订单
        boolean flag = true;
        int count = 1;
        float sum = 0.0f;//订单总金额
        Order order = new Order();//生成的订单
        while (flag) {
            println(getString("product.input.id"));
            String id = input.nextLine();
            println(getString("product.input.shoppingNum"));
            String shoppingNum  = input.nextLine();
            Clothes clothes = clothesService.findClothesById(id);
            int num = Integer.parseInt(shoppingNum);
            if(num>clothes.getNum()){
                throw new BusinessException("product.num.error");
            }
            OrderItem orderItem = new OrderItem();
            //一条订单明细

            clothes.setNum(clothes.getNum()-num);//减库存

            orderItem.setClothes(clothes);
            orderItem.setShoppingNum(num);

            orderItem.setSum(num*clothes.getPrice());
            sum+=orderItem.getSum();
            orderItem.setitemId(count++);
            order.getOrderItemList().add(orderItem);
            println(getString("product.buy.continue"));

            String isBuy = input.nextLine();

            switch (isBuy){
                case "1":
                    flag = true;
                    break;
                case "2":
                    flag = false;
                    break;
                default:
                    flag = false;
                    break;
            }

        }
        order.setCreateData(DateUtils.toDate(new Date()));
        order.setUserID(currentuser.getId());
        order.setSum(sum);
        order.setOrderId(orderService.list().size()+1);
        orderService.buyProduct(order);
        clothesService.update();
        showProducts();
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

        List<Clothes> clothes = clothesService.list();
        // System.out.println(t.toString());

        for(Clothes c:clothes){
            t.appendRow();
            t.appendColum(c.getId()).appendColum(c.getBrand()).appendColum(c.getStyle()).appendColum(c.getColor()).appendColum(c.getSize()).appendColum(c.getNum()).appendColum(c.getPrice()).appendColum(c.getDescription());
        }
        System.out.println(t.toString());
    }

}
