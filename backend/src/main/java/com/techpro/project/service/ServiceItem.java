package com.techpro.project.service;

import com.techpro.project.entity.Item;
import com.techpro.project.entity.People;

import java.util.List;
import java.util.Optional;

public interface ServiceItem {
  //find all items
  List<Item> findAll();
  // Save operation
  Item saveItem(Item item);
  //find by Id
  Optional<Item> findById(Long id);
  // update an item
  Item updateItem(Item item);
  //delete an item
  void deleteById(Long id);
}
