package com.vince.service.impl;

import com.vince.bean.Order;
import com.vince.service.OrderService;
import com.vince.utils.BusinessException;
import com.vince.utils.OrderIO;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderIO orderIo = new OrderIO();

    @Override
    public void buyProduct(Order o) throws BusinessException {
        orderIo.add(o);
    }

    @Override
    public List<Order> list() throws BusinessException {
        return orderIo.list();
    }

    @Override
    public Order findById(String orderId) throws BusinessException {
        return orderIo.findByOrderId(Integer.parseInt(orderId));
    }
}
