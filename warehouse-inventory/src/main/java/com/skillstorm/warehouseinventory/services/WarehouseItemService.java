package com.skillstorm.warehouseinventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouseinventory.models.Item;
import com.skillstorm.warehouseinventory.models.Warehouse;
import com.skillstorm.warehouseinventory.models.WarehouseItem;

import com.skillstorm.warehouseinventory.repositories.WarehouseItemRepository;

@Service
public class WarehouseItemService {

    @Autowired
    WarehouseItemRepository warehouseItemRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    WarehouseService warehouseService;

    // Get all items in this table
    public List<WarehouseItem> findAllWarehouseItems() {
        return warehouseItemRepository.findAll();
    }

    // Return items inventory of one warehouse
    public List<WarehouseItem> findByWarehouse_Id(int id) {
        Optional<List<WarehouseItem>> warehouseItems = warehouseItemRepository.findByWarehouse_Id(id);

        if (warehouseItems.isPresent()) {
            return warehouseItems.get();
        } else {
            return null;
        }
    }

    // Return a single entry by the entry id
    public WarehouseItem findWarehouseItemById(int id) {
        Optional<WarehouseItem> warehouseItem = warehouseItemRepository.findById(id);

        if (warehouseItem.isPresent()) {
            return warehouseItem.get();
        } else {
            return null;
        }
    }

    // Return a single entry by the warehouseId and the itemId
    public WarehouseItem findEntry(int warehouseId, int itemId) {
        Optional<WarehouseItem> warehouseItem = warehouseItemRepository.findByWarehouseAndItem(warehouseId, itemId);

        if (warehouseItem.isPresent()) {
            return warehouseItem.get();
        } else {
            return null;
        }
    }

    // Create an entry
    public int createWarehouseItemBody(WarehouseItem warehouseItem) {
        // Need to store Item into item table
        Item itemWithId = itemService.createItem(warehouseItem.getItem());
        warehouseItem.setItem(itemWithId);

        //update quantity of warehouse
        if(warehouseItem.getWarehouse().updateInventory(warehouseItem.getQuantity()) == 1){
            warehouseItemRepository.save(warehouseItem);
            //will use to denote success in saving
            return 1;
        }
        else{
            //will use to denote error in saving
            return 0;
        }
    }

    // Create an entry by passing it some params
    public int createWarehouseItem(int warehouseId, String itemName, int quantity) {

        // check if warehouse has space
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);

        if (warehouse.updateInventory(quantity) == 1) {

            // Need to store Item into item table
            itemService.createItemByName(itemName);
            // Grab the item just added to table
            Item itemWithId = itemService.findByItemName(itemName);

            // create entry
            warehouseItemRepository.createNewEntry(warehouseId, itemWithId.getId(), quantity);
            return 1;
        } else {
            return 0;
        }
    }

    // Update the quantity of an entry
    public int updateWarehouseItemQuantity(WarehouseItem warehouseItem, int newQuantity) {
        return warehouseItemRepository.updateWarehouseItemQuantity(warehouseItem.getId(), newQuantity);
    }

    // Delete an entry
    public void deleteEntryBody(WarehouseItem warehouseItem) {
        // Item item = itemService.findByItemName(itemName);
        // WarehouseItem warehouseItem = findEntry(warehouseId, item.getId());
        // Subtract the quantity being deleted from the warehouse
        Warehouse warehouse = warehouseItem.getWarehouse();
        int subtract = warehouseItem.getQuantity();
        int newQuantity = warehouse.getCurrentInventory() - subtract;
        warehouseService.updateWarehouseInventory(warehouse, newQuantity);
        warehouseItemRepository.delete(warehouseItem);
    }

    // Delete an entry by passing params
    public void deleteEntry(int warehouseId, String itemName) {
        Item item = itemService.findByItemName(itemName);
        WarehouseItem warehouseItem = findEntry(warehouseId, item.getId());
        warehouseItemRepository.delete(warehouseItem);
    }

}
