package com.skillstorm.warehouseinventory.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column
    @Min(0)
    private int capacity;

    @Column(name = "current_inventory")
    @Min(0)
    private int currentInventory;

    @JsonBackReference
    @OneToMany(targetEntity = WarehouseItem.class, mappedBy = "warehouse")
    private Set<WarehouseItem> warehouseItem;

    public Warehouse() {
    }

    // Adding a warehouse will always just have name + capacity.
    public Warehouse(String name, int capacity) {
        this.warehouseName = name;
        this.capacity = capacity;
    }

    public int updateInventory(int change){
        if(this.currentInventory + change > this.capacity)
        {
            return 0;
        }
        else{
            this.currentInventory += change;
            return 1;
        }
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentInventory() {
        return currentInventory;
    }

    public void setCurrentInventory(int currentInventory) {
        this.currentInventory = currentInventory;
    }

    public Set<WarehouseItem> getWarehouseItem() {
        return warehouseItem;
    }

    public void setWarehouseItem(Set<WarehouseItem> warehouseItem) {
        this.warehouseItem = warehouseItem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((warehouseName == null) ? 0 : warehouseName.hashCode());
        result = prime * result + capacity;
        result = prime * result + currentInventory;
        result = prime * result + ((warehouseItem == null) ? 0 : warehouseItem.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Warehouse other = (Warehouse) obj;
        if (id != other.id)
            return false;
        if (warehouseName == null) {
            if (other.warehouseName != null)
                return false;
        } else if (!warehouseName.equals(other.warehouseName))
            return false;
        if (capacity != other.capacity)
            return false;
        if (currentInventory != other.currentInventory)
            return false;
        if (warehouseItem == null) {
            if (other.warehouseItem != null)
                return false;
        } else if (!warehouseItem.equals(other.warehouseItem))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", warehouseName=" + warehouseName + ", capacity=" + capacity
                + ", currentInventory=" + currentInventory + ", warehouseItem=" + warehouseItem + "]";
    }

}
