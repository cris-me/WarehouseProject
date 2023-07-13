package com.skillstorm.warehouseinventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouseinventory.models.Item;
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
    public WarehouseItem createWarehouseItemBody(WarehouseItem warehouseItem) {
        // Need to store Item into item table
        Item itemWithId = itemService.createItem(warehouseItem.getItem());
        warehouseItem.setItem(itemWithId);

        return warehouseItemRepository.save(warehouseItem);
    }

    // Create an entry by passing it some params
    public WarehouseItem createWarehouseItem(int warehouseId, String itemName, int quantity) {
        // Need to store Item into item table
        itemService.createItemByName(itemName);
        // Grab the item just added to table
        Item itemWithId = itemService.findByItemName(itemName);

        // create entry
        warehouseItemRepository.createNewEntry(warehouseId, itemWithId.getId(), quantity);
        // Grab entry just created
        WarehouseItem warehouseItem = findEntry(warehouseId, itemWithId.getId());

        return warehouseItemRepository.save(warehouseItem);
    }

    // Update the quantity of an entry
    public int updateWarehouseItemQuantity(WarehouseItem warehouseItem, int newQuantity) {
        return warehouseItemRepository.updateWarehouseItemQuantity(warehouseItem.getId(), newQuantity);
    }

    // Delete an entry
    public void deleteEntryBody(WarehouseItem warehouseItem) {
        // Item item = itemService.findByItemName(itemName);
        // WarehouseItem warehouseItem = findEntry(warehouseId, item.getId());
        warehouseItemRepository.delete(warehouseItem);
    }

    // Delete an entry by passing params
    public void deleteEntry(int warehouseId, String itemName) {
        Item item = itemService.findByItemName(itemName);
        WarehouseItem warehouseItem = findEntry(warehouseId, item.getId());
        warehouseItemRepository.delete(warehouseItem);
    }

}
