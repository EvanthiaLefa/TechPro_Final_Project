package com.techpro.project.controllers;

import com.techpro.project.entity.OrderDetails;
import com.techpro.project.service.ServiceOrderDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order_details")
@CrossOrigin(origins = "*")
public class OrderDetailsController {

  private final ServiceOrderDetailsImpl serviceOrderDetails;

  @Autowired
  public OrderDetailsController (ServiceOrderDetailsImpl serviceOrderDetails) {
    this.serviceOrderDetails = serviceOrderDetails;
  }

  /**
   * Retrieves all order details.
   *
   * @return A list of OrderDetails objects.
   */
  @GetMapping(value = "/find_orders_details", produces = "application/json")
  public List<OrderDetails> findAll() {
    return serviceOrderDetails.findAll();
  }

  /**
   * Creates new order details.
   *
   * @param orderDetailsList A List of OrderDetails objects to be created.
   * @return A ResponseEntity indicating the success of the operation.
   */
  @PostMapping(value = "/create_order_details", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> createOrderDetails(@RequestBody List<OrderDetails> orderDetailsList) {
    for (OrderDetails orderDetails : orderDetailsList) {
      serviceOrderDetails.saveOrderDetails(orderDetails);
    }
    return ResponseEntity.ok().build();
  }
}
