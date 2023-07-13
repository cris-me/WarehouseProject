package com.skillstorm.warehouseinventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouseinventory.models.Item;
import com.skillstorm.warehouseinventory.models.WarehouseItem;

import com.skillstorm.warehouseinventory.repositories.ItemRepository;
import com.skillstorm.warehouseinventory.repositories.WarehouseItemRepository;
import com.skillstorm.warehouseinventory.repositories.WarehouseRepository;

@Service
public class WarehouseItemService {
    
    @Autowired
    WarehouseItemRepository warehouseItemRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    // Get all items in this table
    public List<WarehouseItem> findAllWarehouseItems(){
        return warehouseItemRepository.findAll();
    }

    // Return items inventory of one warehouse
    public List<WarehouseItem>  findByWarehouse_Id(int id){
        Optional<List<WarehouseItem>> warehouseItems = warehouseItemRepository.findByWarehouse_Id(id);

        if(warehouseItems.isPresent()){
            return warehouseItems.get();
        }
        else{
            return null;
        }
    }

    // Return a single entry by the entry id
    public WarehouseItem findWarehouseItemById(int id){
        Optional<WarehouseItem> warehouseItem = warehouseItemRepository.findById(id);

        if(warehouseItem.isPresent()){
            return warehouseItem.get();
        }
        else{
            return null;
        }
    }

    // Return a single entry by the warehouseId and the itemId
    public WarehouseItem findEntry(int warehouseId, int itemId){
        Optional<WarehouseItem> warehouseItem = warehouseItemRepository.findByWarehouseAndItem(warehouseId, itemId);

        if(warehouseItem.isPresent()){
            return warehouseItem.get();
        }
        else{
            return null;
        }
    }

    // Create an entry
    public WarehouseItem createWarehouseItem(WarehouseItem warehouseItem){
        //Need to store Item into item table
        Item itemWithId = itemService.createItem(warehouseItem.getItem());
        warehouseItem.setItem(itemWithId);

        return warehouseItemRepository.save(warehouseItem);
    }

    // Update the quantity of an entry
    public int updateWarehouseItemQuantity(WarehouseItem warehouseItem, int newQuantity){
        return warehouseItemRepository.updateWarehouseItemQuantity(warehouseItem.getId(), newQuantity);
    }

}
