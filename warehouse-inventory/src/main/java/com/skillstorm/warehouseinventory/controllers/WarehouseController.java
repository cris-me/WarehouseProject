package com.skillstorm.warehouseinventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouseinventory.models.Warehouse;
import com.skillstorm.warehouseinventory.services.WarehouseService;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin("*")
public class WarehouseController {
    
    @Autowired
    WarehouseService warehouseService;

    // return all warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses(){
        List<Warehouse> warehouses = warehouseService.findAllWarehouses();

        return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
    }

    // return a single warehouse by id
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable int id){
        Warehouse warehouse = warehouseService.findWarehouseById(id);

        return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }
    
    // create a warehouse
    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse){

        Warehouse newWarehouse = warehouseService.createWarehouse(warehouse);

        return new ResponseEntity<Warehouse>(newWarehouse, HttpStatus.CREATED);
    }

    // delete a warehouse
    @DeleteMapping("/warehouse")
    public ResponseEntity<Warehouse> deleteWarehouse(@RequestBody Warehouse warehouse){
        warehouseService.deleteWarehouse(warehouse);
        return ResponseEntity.noContent().build();
    }

    // delete a warehouse by id
    @DeleteMapping("/warehouse/id")
    public ResponseEntity<Warehouse> deleteWarehouseById(@RequestParam int warehouseId){
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        warehouseService.deleteWarehouse(warehouse);
        return ResponseEntity.noContent().build();
    }

    //update the name of a warehouse
    @PutMapping("/warehouse/updateName")
    public ResponseEntity<Integer> updateWarehouse(@RequestParam int warehouseId, @RequestParam String newName){
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        int updated = warehouseService.updateWarehouse(warehouse, newName);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    } 

    // update the capacity
    @PutMapping("/warehouse/updateCapacity")
    public ResponseEntity<Integer> updateWarehouse(@RequestParam int warehouseId, @RequestParam int newCapacity){
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        int updated = warehouseService.updateWarehouse(warehouse, newCapacity);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

    // update the name and capacity of a warehouse
    @PutMapping("/warehouse/update")
    public ResponseEntity<Integer> updateWarehouse(@RequestParam int warehouseId, @RequestParam String newName, @RequestParam int newCapacity){
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        int updated = warehouseService.updateWarehouse(warehouse, newName, newCapacity);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

    
    // A single update function
    // @PutMapping("/warehouse/updateWarehouse")
    // public ResponseEntity<Integer> updateWarehouse(@RequestParam int warehouseId, @RequestParam(required = false) String newName, @RequestParam(required = false) int newCapacity){
    //     int updated;
    //     Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
    //     if(!newName.equals(warehouse.getName()) && newCapacity != warehouse.getCapacity()){
    //         updated = warehouseService.updateWarehouse(warehouse, newName, newCapacity);
    //     }
    //     else if (newCapacity != warehouse.getCapacity()){
    //         updated = warehouseService.updateWarehouse(warehouse, newCapacity);
    //     }
    //     else{
    //         updated = warehouseService.updateWarehouse(warehouse, newName);
    //     }
    //     return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    // }
}
