package com.example.flipkartdaily.util.filterstrategy;

import com.example.flipkartdaily.model.InventoryEntry;
import com.example.flipkartdaily.model.Item;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoryFilter implements Filter {

    @Override
    public List<Item> apply(List<Item> items, Map<Item, InventoryEntry> inventory, List<String> categories) {
        return items.stream()
                .filter(item -> categories.contains(item.getCategory()))
                .collect(Collectors.toList());
    }
}

