package com.techpro.project.testJpa;

import com.techpro.project.repository.OrderRepository;
import com.techpro.project.repository.PeopleRepository;
import com.techpro.project.service.ServicePeopleImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class TestServices {

  ServicePeopleImpl servicePeople;


  PeopleRepository peopleRepository;

  OrderRepository orderRepository;

  @EventListener(ApplicationReadyEvent.class)
  @Transactional
  public void foo() {
    System.out.println("Start FOO");
    //System.out.println(peopleRepository.findAll());


//    orderRepository.deleteById(2L);
//    System.out.println("END FOO");

    //peopleRepository.deleteById(1L);

//    System.out.println("before deleting order");
//    orderRepository.deleteById(1L);
//    System.out.println("after deleteing order");



  }
}
