package com.techpro.project.testJpa;

import com.techpro.project.entity.Order;
import com.techpro.project.entity.OrderDetails;
import com.techpro.project.entity.People;
import com.techpro.project.repository.ItemRepository;
import com.techpro.project.repository.OrderDetailsRepository;
import com.techpro.project.repository.OrderRepository;
import com.techpro.project.repository.PeopleRepository;
import com.techpro.project.service.ServicePeopleImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TestServices {

  ServicePeopleImpl servicePeople;


  PeopleRepository peopleRepository;

  OrderRepository orderRepository;

  OrderDetailsRepository orderDetailsRepository;

  ItemRepository itemRepository;

  @EventListener(ApplicationReadyEvent.class)
  @Transactional
  public void foo() {
    System.out.println("Start FOO");
    //System.out.println(peopleRepository.findAll());

//    OrderDetails orderDetails = orderDetailsRepository.findById(1L).get();
//    List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
//    System.out.println(orderDetails);
//    System.out.println(orderDetailsList);

//    orderRepository.deleteById(2L);
//    System.out.println("END FOO");

    //peopleRepository.deleteById(1L);

//    System.out.println("before deleting order");
//    orderRepository.deleteById(1L);
//    System.out.println("after deleteing order");


    //itemRepository.deleteById(15L);
  }
}
