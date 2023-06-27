package com.techpro.project.controllers;

import com.techpro.project.entity.Order;
import com.techpro.project.entity.People;
import com.techpro.project.service.ServiceOrderImpl;
import com.techpro.project.service.ServicePeopleImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("order")
@CrossOrigin(origins = "*")
public class OrderController {

  private final ServiceOrderImpl serviceOrder;
  private final ServicePeopleImpl servicePeople;

  @Autowired
  public OrderController(ServiceOrderImpl serviceOrder, ServicePeopleImpl servicePeople) {
    this.serviceOrder = serviceOrder;
    this.servicePeople = servicePeople;
  }

  /**
   * Retrieves all orders.
   *
   * @return A list of Order objects.
   */
  @GetMapping(value = "/find_orders", produces = "application/json")
  public List<Order> findAll() {
    return serviceOrder.findAll();
  }

  /**
   * Creates a new order.
   *
   * @param personId A Map containing the personId parameter in the request body.
   * @return A ResponseEntity containing the created Order object with a 200 OK status.
   */
  @PostMapping(value = "/create_order", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Order> create_order(@RequestBody Map<String, Long> personId) {//We could use a PathParam(Rest only) or PathVariable
    Optional<People> existingPeopleOptional = servicePeople.findById(personId.get("personId"));
    People existingPeople = new People();
    if (existingPeopleOptional.isPresent()) {
      existingPeople = existingPeopleOptional.get();
    }

    Order order = new Order(new Date(), existingPeople);

    Order createdOrder = serviceOrder.saveOrder(order);
    return ResponseEntity.ok(createdOrder);
  }

  /**
   * This method accepts a DELETE request at "/delete_order/{id}" with the order ID as a path variable.
   *
   * @param id The ID of the order to be deleted.
   * @return A ResponseEntity indicating the success of the operation.
   */

  @DeleteMapping(value = "/delete_order/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    serviceOrder.deleteById(id);
    return ResponseEntity.ok().build();
  }

}
