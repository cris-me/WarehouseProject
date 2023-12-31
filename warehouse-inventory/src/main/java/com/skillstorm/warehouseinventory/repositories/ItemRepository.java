package com.skillstorm.warehouseinventory.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.warehouseinventory.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    //@Query(value = "SELECT * FROM warehouse_inventory WHERE warehouse_id = :warehouse_id AND item_id = :item_id", nativeQuery = true)
    //public Optional<WarehouseItem> findByWarehouseAndItem(@Param("warehouse_id") int warehouseId, @Param("item_id") int itemId);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO items (item_name) VALUES (:name)", nativeQuery = true)
    public void createItemByName(@Param("name") String itemName);
//    public Optional<List<WarehouseItem>> findByItem_Id(int id);

    @Query(value = "SELECT * FROM items WHERE item_name =:item_name", nativeQuery = true)
    public Optional<Item> findByItem_Name(@Param("item_name") String itemName);
}
