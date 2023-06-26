package com.techpro.project.controllers;

import com.techpro.project.entity.Item;
import com.techpro.project.entity.People;
import com.techpro.project.service.ServiceItemImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("item")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {


  private final ServiceItemImpl serviceItem;

  @Autowired
  public ItemController(ServiceItemImpl serviceItem) {
    this.serviceItem = serviceItem;
  }

  /**
   * Retrieves a list of all items.
   * <p>
   * This method is mapped to the HTTP GET request at "/find_items" and produces a JSON response.
   * It retrieves all items using the serviceItem's findAll() method.
   *
   * @return A List of Item objects representing all the items.
   */
  @GetMapping(value = "/find_items", produces = "application/json")
  public List<Item> findAll() {
    List<Item> itemList = serviceItem.findAll();
    return itemList;
  }

  /**
   * Creates a new item.
   * <p>
   * This method accepts a POST request at "/create_item" with a JSON payload representing the item to be created.
   * The provided item is saved using the ServiceItem's saveItem() method.
   *
   * @param item The Item object representing the item to be created.
   * @return A ResponseEntity indicating the success of the operation.
   */

  @PostMapping(value = "/create_item", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> createItem(@RequestBody Item item) {
    serviceItem.saveItem(item);
    return ResponseEntity.ok().build();
  }

  /**
   * Retrieves an item by its ID.
   * <p>
   * This method accepts a GET request at "/get_item/{id}" with the item ID as a path variable.
   * The method calls the ServiceItem's findById() method to retrieve the item with the specified ID.
   *
   * @param id The ID of the item to be retrieved.
   * @return An Optional containing the Item object if found, or an empty Optional if not found.
   */

  @GetMapping(value = "/get_item/{id}", produces = "application/json")
  public Optional<Item> findById(@PathVariable Long id) {
    System.out.println("id");
    return serviceItem.findById(id);
  }

  /**
   * Updates an item.
   * <p>
   * This method accepts a PUT request at "/update_item" with a JSON payload representing the updated item.
   *
   * @param updatedItem The Item object representing the updated item.
   * @return A ResponseEntity indicating the success of the operation.
   */

  @PutMapping(value = "/update_item", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> updateItem(@RequestBody Item updatedItem) {
    serviceItem.updateItem(updatedItem);
    return ResponseEntity.ok().build();
  }

  /**
   * Deletes an item by its ID.
   * <p>
   * This method accepts a DELETE request at "/delete_item/{id}" with the item ID as a path variable.
   *
   * @param id The ID of the item to be deleted.
   * @return A ResponseEntity indicating the success of the operation.
   */

  @DeleteMapping(value = "/delete_item/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    serviceItem.deleteById(id);
    return ResponseEntity.ok().build();
  }


}
