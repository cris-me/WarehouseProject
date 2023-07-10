package com.skillstorm.warehouseinventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouseinventory.models.Warehouse;
import com.skillstorm.warehouseinventory.repositories.WarehouseRepository;


@Service
public class WarehouseService {
    
    @Autowired
    WarehouseRepository warehouseRepository;

    // To return all warehouses
    public List<Warehouse> findAllWarehouses(){
        return warehouseRepository.findAll();
    }

    // To return a single warehouse by id
    public Warehouse findWarehouseById(int id){
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if(warehouse.isPresent()){
            return warehouse.get();
        }
        else{
            return null;
        }
    }

    // To create a warehouse, just using JPA save
    public Warehouse createWarehouse(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    // To delete a warehouse, just using JPA delete
    public void deleteWarehouse(Warehouse warehouse){
        warehouseRepository.delete(warehouse);
    }

    // Update the name and or capacity of a warehouse
    public int updateWarehouse(Warehouse warehouse, String newName){
        return warehouseRepository.updateWarehouse(warehouse.getId(), newName);
    }

    public int updateWarehouse(Warehouse warehouse, int newCapacity){
        return warehouseRepository.updateWarehouse(warehouse.getId(), newCapacity);
    }

    public int updateWarehouse(Warehouse warehouse, String newName, int newCapacity){
        return warehouseRepository.updateWarehouse(warehouse.getId(), newName, newCapacity);
    }
}
