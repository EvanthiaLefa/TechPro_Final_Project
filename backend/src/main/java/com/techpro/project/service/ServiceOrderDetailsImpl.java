package com.techpro.project.service;

import com.techpro.project.entity.OrderDetails;
import com.techpro.project.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceOrderDetailsImpl implements ServiceOrderDetails{

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public  OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> findAll(){
        return orderDetailsRepository.findAll();
    }
}
