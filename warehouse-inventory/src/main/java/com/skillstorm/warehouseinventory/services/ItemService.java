package com.skillstorm.warehouseinventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouseinventory.models.Item;
import com.skillstorm.warehouseinventory.repositories.ItemRepository;


@Service
public class ItemService {
    
    @Autowired
    ItemRepository itemRepository;

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }
}
