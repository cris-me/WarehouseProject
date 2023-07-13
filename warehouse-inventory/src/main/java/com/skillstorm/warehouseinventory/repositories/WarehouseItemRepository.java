package com.skillstorm.warehouseinventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.skillstorm.warehouseinventory.models.WarehouseItem;

@Repository
public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Integer>{
    
    public Optional<List<WarehouseItem>> findByWarehouse_Id(int id);
    
    public Optional<List<WarehouseItem>> findByItem_Id(int id);

    @Query("update WarehouseItem i set i.quantity = :new_quantity where id = :inventory_id")
    @Modifying
    @Transactional
    public int updateWarehouseItemQuantity(@Param("inventory_id") int id, @Param("new_quantity") int newQuantity);

    //Grab an entry with warehouse_id AND item_id
    @Query(value = "SELECT * FROM warehouse_inventory WHERE warehouse_id = :warehouse_id AND item_id = :item_id", nativeQuery = true)
    public Optional<WarehouseItem> findByWarehouseAndItem(@Param("warehouse_id") int warehouseId, @Param("item_id") int itemId);


    //  @Query(value = "INSERT INTO items (item_name) VALUES (':item_name')", nativeQuery = true)
    //  public void createItemByName(@Param("item_name") String itemName);

    //Create an entry
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO warehouse_inventory (warehouse_id, item_id, quantity) VALUES (:warehouse_id, :item_id, :quantity)", nativeQuery = true)
    public void createNewEntry(@Param("warehouse_id") int warehouseId, @Param("item_id") int itemId, @Param("quantity") int quantity);
    
}
