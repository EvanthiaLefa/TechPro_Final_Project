package com.techpro.project.service;

import com.techpro.project.entity.Item;
import com.techpro.project.entity.People;
import com.techpro.project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceItemImpl implements ServiceItem {
  private final ItemRepository itemRepository;

  @Autowired
  ServiceItemImpl(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  /**
   * Retrieves a list of all items.
   * <p>
   * It retrieves all items using the ItemRepository's findAll() method.
   *
   * @return A List of Item objects representing all the items.
   */
  public List<Item> findAll() {
    return itemRepository.findAll();
  }

  /**
   * Saves an item.
   * <p>
   * This method saves the provided item using the ItemRepository's save() method.
   *
   * @param item The item to be saved.
   * @return The saved Item object.
   */

  public Item saveItem(Item item) {
    return itemRepository.save(item);
  }

  /**
   * Finds an item by its ID.
   * <p>
   * This method retrieves the item with the specified ID using the ItemRepository's findById() method.
   *
   * @param id The ID of the item to be retrieved.
   * @return An Optional containing the Item object if found, or an empty Optional if not found.
   */

  public Optional<Item> findById(Long id) {
    return itemRepository.findById(id);
  }

  /**
   * Updates an item.
   * <p>
   * This method updates the provided item using the ItemRepository's save() method.
   * If the item already exists (based on its ID), it will be updated; otherwise, a new item will be created.
   *
   * @param item The item to be updated.
   * @return The updated Item object.
   */

  public Item updateItem(Item item) {
    //JPA understands if id exists so it upddates
    itemRepository.save(item);
    return item;
  }

  /**
   * Deletes an item by its ID.
   * <p>
   * This method deletes the item with the specified ID using the ItemRepository's deleteById() method.
   *
   * @param id The ID of the item to be deleted.
   */
  public void deleteById(Long id) {
    itemRepository.deleteById(id);
  }
}
