package com.example.flipkartdaily.util;

import com.example.flipkartdaily.model.InventoryEntry;
import com.example.flipkartdaily.model.Item;
import com.example.flipkartdaily.util.sortstrategy.PriceSortStrategy;
import com.example.flipkartdaily.util.sortstrategy.QuantitySortStrategy;
import com.example.flipkartdaily.util.sortstrategy.SortStrategy;

import java.util.Comparator;
import java.util.Map;

public class SortUtil {

    public static Comparator<Item> getComparator(OrderBy orderBy, OrderDir orderDir, Map<Item, InventoryEntry> inventory) {
        SortStrategy strategy = switch (orderBy) {
            case PRICE -> new PriceSortStrategy();
            case ITEM_QTY -> new QuantitySortStrategy();
        };

        return strategy.getComparator(inventory, orderDir);
    }
}
