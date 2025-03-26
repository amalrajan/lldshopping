package com.example.flipkartdaily.util.filterstrategy;

import com.example.flipkartdaily.model.InventoryEntry;
import com.example.flipkartdaily.model.Item;
import java.util.List;
import java.util.Map;

public interface Filter {
    List<Item> apply(List<Item> items, Map<Item, InventoryEntry> inventory, List<String> criteria);
}
