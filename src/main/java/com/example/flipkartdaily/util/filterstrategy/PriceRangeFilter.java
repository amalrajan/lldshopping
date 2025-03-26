package com.example.flipkartdaily.util.filterstrategy;

import com.example.flipkartdaily.model.InventoryEntry;
import com.example.flipkartdaily.model.Item;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PriceRangeFilter implements Filter {

    @Override
    public List<Item> apply(List<Item> items, Map<Item, InventoryEntry> inventory, List<String> priceRange) {
        if (priceRange.size() != 2) {
            throw new IllegalArgumentException("Price range must have exactly 2 values (min and max).");
        }

        double minPrice = Double.parseDouble(priceRange.get(0));
        double maxPrice = Double.parseDouble(priceRange.get(1));

        return items.stream()
                .filter(item -> {
                    InventoryEntry entry = inventory.get(item);
                    if (entry == null) {
                        return false;
                    }
                    double price = entry.getPrice();
                    return price >= minPrice && price <= maxPrice;
                })
                .collect(Collectors.toList());
    }
}
