package com.example.flipkartdaily.service;

import com.example.flipkartdaily.dao.InventoryDao;
import com.example.flipkartdaily.exception.InventoryException;
import com.example.flipkartdaily.model.InventoryEntry;
import com.example.flipkartdaily.model.Item;
import com.example.flipkartdaily.util.FilterKey;
import com.example.flipkartdaily.util.OrderBy;
import com.example.flipkartdaily.util.OrderDir;
import com.example.flipkartdaily.util.SortUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryService {

    private final InventoryDao inventoryDAO;

    public InventoryService(InventoryDao inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    // Add a new item to inventory with price and default quantity 0
    public void addItem(Item item, double price) {
        inventoryDAO.addItem(item, price);
    }

    public InventoryEntry getInventoryEntry(Item item) {
        return inventoryDAO.getInventoryEntry(item);
    }

    public void updatePrice(Item item, double newPrice) {
        if (inventoryDAO.containsItem(item)) {
            inventoryDAO.updatePrice(item, newPrice);
        } else {
            throw new InventoryException("Item not found. Cannot update price.");
        }
    }

    public void addInventory(Item item, int quantity) {
        inventoryDAO.addInventory(item, quantity);
    }

    public List<Item> searchItems(Map<FilterKey, List<String>> filters, OrderBy orderBy, OrderDir orderDir) {
        Map<Item, InventoryEntry> inventoryMap = inventoryDAO.getInventoryMap();
        List<Item> filteredItems = inventoryDAO.searchItems(filters);

        // Set default orderBy and orderDir if null
        if (orderBy == null) {
            orderBy = OrderBy.PRICE; // Default to price
        }
        if (orderDir == null) {
            orderDir = OrderDir.ASC; // Default to ascending
        }

        Comparator<Item> comparator = SortUtil.getComparator(orderBy, orderDir, inventoryMap);
        if (comparator != null) {
            filteredItems.sort(comparator);
        }

        return filteredItems;
    }
}
