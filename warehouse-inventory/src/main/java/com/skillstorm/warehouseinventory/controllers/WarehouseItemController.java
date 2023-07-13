package com.skillstorm.warehouseinventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouseinventory.models.WarehouseItem;
import com.skillstorm.warehouseinventory.services.WarehouseItemService;

@RestController
@RequestMapping("/warehouseItems")
public class WarehouseItemController {

    @Autowired
    WarehouseItemService warehouseItemService;

    // Get all inventory
    @GetMapping
    public ResponseEntity<List<WarehouseItem>> findAllWarehouseItems() {
        List<WarehouseItem> warehouseItem = warehouseItemService.findAllWarehouseItems();

        return new ResponseEntity<List<WarehouseItem>>(warehouseItem, HttpStatus.OK);
    }

    // Get inventory for one warehouse
    @GetMapping("/{warehouseId}")
    public ResponseEntity<List<WarehouseItem>> findByWarehouse_Id(@PathVariable int warehouseId) {
        List<WarehouseItem> warehouseItems = warehouseItemService.findByWarehouse_Id(warehouseId);

        if (warehouseItems == null) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<List<WarehouseItem>>(warehouseItems, HttpStatus.OK);
    }

    // Create an entry
    @PostMapping("/warehouseItem/body")
    public ResponseEntity<WarehouseItem> createWarehouseItemBody(@RequestBody WarehouseItem warehouseItem) {
        WarehouseItem newWarehouseItem = warehouseItemService.createWarehouseItemBody(warehouseItem);

        return new ResponseEntity<WarehouseItem>(newWarehouseItem, HttpStatus.CREATED);
    }

    // Create an entry by passing in the params
    @PostMapping("/warehouseItem")
    public ResponseEntity<WarehouseItem> createWarehouseItem(@RequestParam int warehouseId,
            @RequestParam String itemName, @RequestParam int quantity) {

        WarehouseItem newWarehouseItem = warehouseItemService.createWarehouseItem(warehouseId, itemName, quantity);

        return new ResponseEntity<WarehouseItem>(newWarehouseItem, HttpStatus.CREATED);
    }

    // Update the quantity of an entry
    @PutMapping("/warehouseItem/updateQuantity")
    public ResponseEntity<Integer> updateWarehouseItemQuantityBody(@RequestBody WarehouseItem warehouseItem,
            @RequestParam int newQuantity) {
        int updated = warehouseItemService.updateWarehouseItemQuantity(warehouseItem, newQuantity);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

    // Update the quantity of an entry by passing in the params for a search
    @PutMapping("/warehouseItem/updateQuantity")
    public ResponseEntity<Integer> updateWarehouseItemQuantity(@RequestParam int warehouseId, @RequestParam int itemId,
            @RequestParam int newQuantity) {
        WarehouseItem warehouseItem = warehouseItemService.findEntry(warehouseId, itemId);
        int updated = warehouseItemService.updateWarehouseItemQuantity(warehouseItem, newQuantity);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

    // Delete an entry
    @DeleteMapping("/warehouseItem")
    public ResponseEntity<WarehouseItem> deleteWarehouseItemBody(@RequestBody WarehouseItem warehouseItem) {
        warehouseItemService.deleteEntryBody(warehouseItem);
        return ResponseEntity.noContent().build();
    }

    // Delete an entry by passing in params
    @DeleteMapping("/warehouseItem")
    public ResponseEntity<WarehouseItem> deleteWarehouseItem(@RequestParam int warehouseId,
            @RequestParam String itemName) {
        warehouseItemService.deleteEntry(warehouseId, itemName);
        return ResponseEntity.noContent().build();
    }

}
