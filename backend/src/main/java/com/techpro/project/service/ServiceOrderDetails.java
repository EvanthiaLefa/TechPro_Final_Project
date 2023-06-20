package com.techpro.project.service;

import com.techpro.project.entity.OrderDetails;

import java.util.List;

public interface ServiceOrderDetails {

    OrderDetails saveOrderDetails(OrderDetails orderDetails);

    List<OrderDetails> findAll();
}
