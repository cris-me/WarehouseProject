package com.skillstorm.warehouseinventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouseinventory.models.Item;
import com.skillstorm.warehouseinventory.services.ItemService;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> findAllItems(){
        List<Item> items = itemService.findAllItems();

        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

}
