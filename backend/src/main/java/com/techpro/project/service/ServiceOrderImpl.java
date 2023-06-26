package com.techpro.project.service;

import com.techpro.project.entity.Order;

import com.techpro.project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceOrderImpl implements ServiceOrder {

  private final OrderRepository orderRepository;

  @Autowired
  public ServiceOrderImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order saveOrder(Order order) {
    return orderRepository.save(order);
  }

  public List<Order> findAll() {
    return orderRepository.findAll();
  }

}
