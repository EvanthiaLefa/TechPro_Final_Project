package com.techpro.project.controllers;

import com.techpro.project.entity.Item;
import com.techpro.project.service.ServiceItemImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    @Autowired
    private final ServiceItemImpl serviceItem;

    /**
     * Retrieves a list of all items.
     *
     * This method is mapped to the HTTP GET request at "/find_items" and produces a JSON response.
     * It retrieves all items using the serviceItem's findAll() method.
     *
     * @return A List of Item objects representing all the items.
     */
    @GetMapping(value = "/find_items" , produces = "application/json")
    public List<Item> findAll()
    {
        return serviceItem.findAll();
    }


}
