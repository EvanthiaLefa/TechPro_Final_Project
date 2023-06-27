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

  /**
   * Saves an order.
   * @param order The order to be saved.
   * @return The saved order object.
   */
  public Order saveOrder(Order order) {
    return orderRepository.save(order);
  }

  /**
   * Retrieves a list of all orders.
   * <p>
   * It retrieves all orders using the OrderRepository's findAll() method.
   * @return A List of Order objects representing all the orders.
   */
  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  /**
   * Deletes an order by its ID.
   * @param id The ID of the order to be deleted.
   */
  public void deleteById(Long id) {
    orderRepository.deleteById(id);
  }

}
