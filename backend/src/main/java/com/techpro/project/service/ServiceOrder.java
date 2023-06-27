package com.techpro.project.service;

import com.techpro.project.entity.Order;


import java.util.List;

public interface ServiceOrder {
    Order saveOrder(Order order);
    List<Order> findAll();
    void deleteById(Long id);
}
