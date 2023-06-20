package com.techpro.project.controllers;

import com.techpro.project.entity.Item;
import com.techpro.project.entity.People;
import com.techpro.project.service.ServiceItem;
import com.techpro.project.service.ServiceItemImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item")
@AllArgsConstructor
public class ItemController {

    @Autowired
    private final ServiceItemImpl serviceItem;

    @GetMapping(value = "/find_items" , produces = "application/json")
    public List<Item> findAll()
    {
        return serviceItem.findAll();
    }


}
