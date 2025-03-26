package com.example.flipkartdaily.util.sortstrategy;
import com.example.flipkartdaily.model.InventoryEntry;
import com.example.flipkartdaily.model.Item;
import com.example.flipkartdaily.util.OrderDir;

import java.util.Comparator;
import java.util.Map;

public interface SortStrategy {
    Comparator<Item> getComparator(Map<Item, InventoryEntry> inventory, OrderDir orderDir);
}
