package com.example.flipkartdaily.dao;

import com.example.flipkartdaily.exception.InventoryException;
import com.example.flipkartdaily.model.InventoryEntry;
import com.example.flipkartdaily.model.Item;
import com.example.flipkartdaily.util.filterstrategy.PriceRangeFilter;
import com.example.flipkartdaily.util.filterstrategy.BrandFilter;
import com.example.flipkartdaily.util.filterstrategy.CategoryFilter;
import com.example.flipkartdaily.util.filterstrategy.Filter;
import com.example.flipkartdaily.util.FilterKey;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InventoryDao {
    private final Map<Item, InventoryEntry> inventoryMap = new HashMap<>();

    public void addItem(Item item, double price) {
        if (inventoryMap.containsKey(item)) {
            throw new InventoryException("Item already exists. Use updatePrice to modify the price.",
                    InventoryException.ErrorCode.DUPLICATE_ITEM);
        }
        if (price <= 0) {
            throw new InventoryException("Price must be greater than zero.", InventoryException.ErrorCode.INVALID_PRICE);
        }
        inventoryMap.put(item, new InventoryEntry(price, 0));
    }

    public void addInventory(Item item, int quantity) {
        if (quantity <= 0) {
            throw new InventoryException("Quantity must be greater than zero.", InventoryException.ErrorCode.INVALID_QUANTITY);
        }
        if (inventoryMap.containsKey(item)) {
            InventoryEntry entry = inventoryMap.get(item);
            entry.setQuantity(entry.getQuantity() + quantity);
        } else {
            throw new InventoryException("Item not found. Add the item first before adding inventory.",
                    InventoryException.ErrorCode.ITEM_NOT_FOUND);
        }
    }

    public InventoryEntry getInventoryEntry(Item item) {
        return inventoryMap.get(item);
    }

    // Check if item exists in the inventory
    public boolean containsItem(Item item) {
        return inventoryMap.containsKey(item);
    }

    public List<Item> searchItems(Map<FilterKey, List<String>> filters) {
        List<Item> filteredKeys = new ArrayList<>(inventoryMap.keySet());

        // Apply filters dynamically
        for (Map.Entry<FilterKey, List<String>> entry : filters.entrySet()) {
            Filter filter = getFilter(entry.getKey());
            filteredKeys = filter.apply(filteredKeys, inventoryMap, entry.getValue());
        }

        return filteredKeys;
    }

    private Filter getFilter(FilterKey key) {
        return switch (key) {
            case CATEGORY -> new CategoryFilter();
            case BRAND -> new BrandFilter();
            case PRICE -> new PriceRangeFilter();
        };
    }

    public void updatePrice(Item item, double newPrice) {
        if (newPrice <= 0) {
            throw new InventoryException("Price must be greater than zero.", InventoryException.ErrorCode.INVALID_PRICE);
        }
        if (inventoryMap.containsKey(item)) {
            InventoryEntry entry = inventoryMap.get(item);
            entry.setPrice(newPrice);
        } else {
            throw new InventoryException("Item not found. Cannot update price.", InventoryException.ErrorCode.ITEM_NOT_FOUND);
        }
    }

    // Return inventory map for reference
    public Map<Item, InventoryEntry> getInventoryMap() {
        return inventoryMap;
    }
}
