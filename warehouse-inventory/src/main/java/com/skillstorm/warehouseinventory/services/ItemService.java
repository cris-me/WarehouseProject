package com.skillstorm.warehouseinventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouseinventory.models.Item;
import com.skillstorm.warehouseinventory.repositories.ItemRepository;

/*
 * Item operations
 *      No update because I intend for items to
 *      be immutable. The junction table will store
 *      their quantity per warehouse. An item needing a
 *      name change will necessitate an item being deleted 
 *      and recreated.
 */

@Service
public class ItemService {
    
    @Autowired
    ItemRepository itemRepository;

    // To return all items
    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    // To return a single item by id
    public Item findItemById(int id){
        Optional<Item> item = itemRepository.findById(id);
        
        if(item.isPresent()){
            return item.get();
        }
        else{
            return null;
        }
    }

    //TODO: To return a single item by name 

    // To create an item, using JPA save
    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    //To delete an item, just using JPA delete
    public void deleteItem(Item item){
        itemRepository.delete(item);
    }

    
}
