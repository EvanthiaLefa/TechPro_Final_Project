package com.techpro.project.controllers;

import com.techpro.project.entity.Order;
import com.techpro.project.entity.OrderDetails;
import com.techpro.project.service.ServiceOrderDetailsImpl;
import com.techpro.project.service.ServiceOrderImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("order_details")
public class OrderDetailsController {

    @Autowired
    private final ServiceOrderDetailsImpl serviceOrderDetails;

    @GetMapping(value = "/find_orders_details" , produces = "application/json")
    public List<OrderDetails> findAll()
    {
        return serviceOrderDetails.findAll();
    }

    @PostMapping(value = "/create_order_details",consumes = "application/json" , produces = "application/json")
    public ResponseEntity<?> test_controller3(@RequestBody OrderDetails orderDetails) {
        System.out.println(orderDetails);
        serviceOrderDetails.saveOrderDetails(orderDetails);
        return ResponseEntity.ok().build();
    }
}
