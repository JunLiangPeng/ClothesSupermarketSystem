package com.vince.ui;


import com.vince.bean.Clothes;
import com.vince.bean.Order;
import com.vince.bean.OrderItem;
import com.vince.service.ClothesService;
import com.vince.service.OrderService;

import com.vince.utils.BusinessException;
import com.vince.utils.ConsoleTable;
import com.vince.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class HomeClass extends Base{


    private OrderService orderService;
    private ClothesService clothesService;
    public HomeClass(){
        orderService = (OrderService)beanFactory.getBean("orderService");
        clothesService = (ClothesService)beanFactory.getBean("clothesService");
    }


    public void show(){
        showProducts();
        println("welcome:"+currentuser.getUsername());
        menu();

    }

    private void menu() {
        boolean flag = true;
        while(flag){
            println(getString("home.function"));
            println(getString("info.select"));
            String select = input.nextLine();

            switch(select){
                case "1"://1、查询全部订单
                    findOrderList();
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
                case "4":
                    show();
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
        show();
    }

    //根据id查找订单
    private void findOrderById() {
        println(getString("product.order.input.oid"));
        String oid = input.nextLine();
        Order order = orderService.findById(oid);
        if(order == null){
            showOrder(order);
        } else {
            println(getString("product.order.error"));
        }
        menu();
    }

    //查询全部订单
    private void findOrderList() {
        List<Order> list = orderService.list();
        for(Order o:list){
            showOrder(o);
        }
         menu();
    }

    private void showOrder(Order o) {

        println(getString("product.order.oid"));
        println("\t"+getString("product.order.createDate")+o.getCreateData());
        println("\t"+getString("product.order.sum")+o.getSum());
        ConsoleTable t = new ConsoleTable(9, true);
        t.appendRow();
        t.appendColum("itemId")
                .appendColum("brand")
                .appendColum("style")
                .appendColum("color")
                .appendColum("size")
                .appendColum("price")
                .appendColum("description")
                .appendColum("shoppingNum")
                .appendColum("sum");
        List<Clothes> clothes = clothesService.list();

        for(Clothes c:clothes){
            t.appendRow();
            t.appendColum(c.getId()).appendColum(c.getBrand()).appendColum(c.getStyle()).appendColum(c.getColor()).appendColum(c.getSize()).appendColum(c.getNum()).appendColum(c.getPrice()).appendColum(c.getDescription());
        }
        System.out.println(t.toString());
    }

    private void showProducts(){

        ConsoleTable t = new ConsoleTable(8, true);
        t.appendRow();
        t.appendColum("id").appendColum("brand").appendColum("style").appendColum("color").appendColum("size").appendColum("num").appendColum("price").appendColum("description");

        List<Clothes> clothes = clothesService.list();

        for(Clothes c:clothes){
            t.appendRow();
            t.appendColum(c.getId()).appendColum(c.getBrand()).appendColum(c.getStyle()).appendColum(c.getColor()).appendColum(c.getSize()).appendColum(c.getNum()).appendColum(c.getPrice()).appendColum(c.getDescription());
        }
        System.out.println(t.toString());
    }

}
