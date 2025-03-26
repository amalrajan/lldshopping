package com.example.flipkartdaily.util.sortstrategy;

import com.example.flipkartdaily.model.InventoryEntry;
import com.example.flipkartdaily.model.Item;
import com.example.flipkartdaily.util.OrderDir;

import java.util.Comparator;
import java.util.Map;

public class QuantitySortStrategy implements SortStrategy {

    @Override
    public Comparator<Item> getComparator(Map<Item, InventoryEntry> inventory, OrderDir orderDir) {
        Comparator<Item> comparator = Comparator.comparing(item -> {
            InventoryEntry entry = inventory.get(item);
            return (entry != null) ? entry.getQuantity() : 0;
        });

        return (orderDir == OrderDir.DESC) ? comparator.reversed() : comparator;
    }
}

