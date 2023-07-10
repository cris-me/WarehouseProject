package com.skillstorm.warehouseinventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouseinventory.models.Warehouse;
import com.skillstorm.warehouseinventory.repositories.WarehouseRepository;


@Service
public class WarehouseService {
    
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> findAllWarehouses(){
        return warehouseRepository.findAll();
    }
}
