package com.skillstorm.warehouseinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.warehouseinventory.models.Warehouse;

/*
 * Need to be able to update a warehouse's name, capacity
 * 
 */
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

    //updating the name of a warehouse
    @Query("update Warehouse w set w.warehouseName = :new_name where id = :warehouse_id")
    @Modifying
    @Transactional
    public int updateWarehouse(@Param("warehouse_id") int id, @Param("new_name") String newWarehouseName);

    //updating the capacity of a warehouse
    @Query("update Warehouse w set w.capacity = :new_capacity where id = :warehouse_id")
    @Modifying
    @Transactional
    public int updateWarehouse(@Param("warehouse_id") int id, @Param("new_capacity") int newWarehouseCapacity);

    //updating the name and capacity of a warehouse

    @Query("update Warehouse w set w.warehouseName = :new_name, w.capacity = :new_capacity where id = :warehouse_id")
    @Modifying
    @Transactional
    public int updateWarehouse(@Param("warehouse_id") int id, @Param("new_name") String newWarehouseName, @Param("new_capacity") int newWarehouseCapacity);
}
