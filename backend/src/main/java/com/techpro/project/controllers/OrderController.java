package com.techpro.project.controllers;

import com.techpro.project.entity.Order;
import com.techpro.project.entity.People;
import com.techpro.project.service.ServiceOrderImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private final ServiceOrderImpl serviceOrder;

    @GetMapping(value = "/find_orders" , produces = "application/json")
    public List<Order> findAll()
    {
        return serviceOrder.findAll();
    }

    @PostMapping(value = "/create_order",consumes = "application/json" , produces = "application/json")
    public ResponseEntity<?> create_order(@RequestBody Order order) {
        System.out.println(order);
        serviceOrder.saveOrder(order);
        return ResponseEntity.ok().build();
    }

}
