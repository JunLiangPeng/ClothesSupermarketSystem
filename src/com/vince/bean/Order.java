package com.vince.bean;

import com.vince.utils.OrderStatusType;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<OrderItem> orderItemList = new ArrayList<>();
    private String createData;
    private float sum;//总金额
    private OrderStatusType status = OrderStatusType.UNPAID;//状态
    private int userID;

    public Order() {
    }

    public Order(int orderId, List<OrderItem> orderItemList, String createData, float sum, OrderStatusType status, int userID) {
        this.orderId = orderId;
        this.orderItemList = orderItemList;
        this.createData = createData;
        this.sum = sum;
        this.status = status;
        this.userID = userID;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
