package com.skillstorm.warehouseinventory.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name")
    private String itemName;

    @JsonBackReference
    @OneToMany(targetEntity = WarehouseItem.class, mappedBy = "item")
    private Set<WarehouseItem> warehouseItems;

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Item(int id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<WarehouseItem> getWarehouseItems() {
        return warehouseItems;
    }

    public void setWarehouseItems(Set<WarehouseItem> warehouseItems) {
        this.warehouseItems = warehouseItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
        result = prime * result + ((warehouseItems == null) ? 0 : warehouseItems.hashCode());
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
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        if (itemName == null) {
            if (other.itemName != null)
                return false;
        } else if (!itemName.equals(other.itemName))
            return false;
        if (warehouseItems == null) {
            if (other.warehouseItems != null)
                return false;
        } else if (!warehouseItems.equals(other.warehouseItems))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", itemName=" + itemName + ", warehouseItems=" + warehouseItems + "]";
    }

}
