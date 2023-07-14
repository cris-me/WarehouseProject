package com.skillstorm.warehouseinventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouseinventory.models.Item;
import com.skillstorm.warehouseinventory.services.ItemService;

@RestController
@RequestMapping("/items")
@CrossOrigin("*")
public class ItemController {

    @Autowired
    ItemService itemService;

    // returns all items
    @GetMapping
    public ResponseEntity<List<Item>> findAllItems(){
        List<Item> items = itemService.findAllItems();

        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    // returns a single item by id
    @GetMapping("/item")
    public ResponseEntity<Item> findItemById(@RequestParam int itemId){
        Item item = itemService.findItemById(itemId);

        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    //find an item by name
    @GetMapping("/item/name")
    public ResponseEntity<Item> findItemByName(@RequestParam String itemName){
        Item item = itemService.findByItemName(itemName);

        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    // create an item
    @PostMapping("/item")
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item newItem = itemService.createItem(item);

        return new  ResponseEntity<Item>(newItem, HttpStatus.CREATED);
    }

    // create an item by name param
    @PostMapping("/item/name")
    public ResponseEntity<Item> createItemByName(@RequestParam String itemName){
        itemService.createItemByName(itemName);
        Item newItem = itemService.findByItemName(itemName);

        return new  ResponseEntity<Item>(newItem, HttpStatus.CREATED);
    }

    // delete an item
    @DeleteMapping("/item")
    public ResponseEntity<Item> deleteItem(@RequestBody Item item){
        itemService.deleteItem(item);
        return ResponseEntity.noContent().build();
    }

    // delete an item by id
    @DeleteMapping("/item/id")
    public ResponseEntity<Item> deleteItemById(@RequestParam int itemId){
        Item item = itemService.findItemById(itemId);
        itemService.deleteItem(item);
        return ResponseEntity.noContent().build();
    }
}
