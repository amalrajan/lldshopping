package com.example.flipkartdaily;

import com.example.flipkartdaily.exception.InventoryException;
import com.example.flipkartdaily.model.Item;
import com.example.flipkartdaily.service.InventoryService;
import com.example.flipkartdaily.util.FilterKey;
import com.example.flipkartdaily.util.OrderBy;
import com.example.flipkartdaily.util.OrderDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class FlipkartdailyApplication implements CommandLineRunner {
    private final InventoryService service;

    public FlipkartdailyApplication(InventoryService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(FlipkartdailyApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Declare items first
        Item milkAmul = new Item("Milk", "Amul");
        Item curdAmul = new Item("Curd", "Amul");
        Item milkNestle = new Item("Milk", "Nestle");
        Item curdNestle = new Item("Curd", "Nestle");

        // Add items with price
        service.addItem(milkAmul, 100.0);
        service.addItem(curdAmul, 50.0);
        service.addItem(milkNestle, 60.0);
        service.addItem(curdNestle, 90.0);

        // Add inventory (increase quantity)
        service.addInventory(milkAmul, 10);
        service.addInventory(milkAmul, 10);
        service.addInventory(milkNestle, 5);
        service.addInventory(curdAmul, 5);
        service.addInventory(curdNestle, 10);

        // Search by Brand (Nestle) and Order by Price Ascending
        Map<FilterKey, List<String>> filter1 = new HashMap<>();
        filter1.put(FilterKey.BRAND, List.of("Nestle"));
        List<Item> result1 = service.searchItems(filter1, OrderBy.PRICE, OrderDir.ASC);
        System.out.println("Search by Brand (Nestle) - PRICE ASC:");
        printResults(result1);

        // Search by Category (Milk) and Order by Price Ascending
        Map<FilterKey, List<String>> filter2 = new HashMap<>();
        filter2.put(FilterKey.CATEGORY, List.of("Milk"));
        List<Item> result2 = service.searchItems(filter2, OrderBy.PRICE, OrderDir.ASC);
        System.out.println("\nSearch by Category (Milk) - PRICE ASC:");
        printResults(result2);

        // Search by Price Range (70.0 to 100.0) and Order by Price Ascending
        Map<FilterKey, List<String>> filter3 = new HashMap<>();
        filter3.put(FilterKey.PRICE, Arrays.asList("70.0", "100.0"));
        List<Item> result3 = service.searchItems(filter3, OrderBy.PRICE, OrderDir.ASC);
        System.out.println("\nSearch by Price Range (70.0 to 100.0) - PRICE ASC:");
        printResults(result3);

        // Search by Category (Milk) and Order by Quantity Descending
        Map<FilterKey, List<String>> filter4 = new HashMap<>();
        filter4.put(FilterKey.CATEGORY, List.of("Milk"));
        List<Item> result4 = service.searchItems(filter4, OrderBy.ITEM_QTY, OrderDir.DESC);
        System.out.println("\nSearch by Category (Milk) - ITEM QTY DESC:");
        printResults(result4);

        // Update price for an item
        service.updatePrice(milkAmul, 999.99);
        System.out.println("\nUpdated Price for Milk (Amul):");
        List<Item> result5 = service.searchItems(filter2, OrderBy.PRICE, OrderDir.ASC);
        printResults(result5);

//        try {
//            service.addItem(new Item("Milk", "Amul"), 100.0);
//            service.addInventory(new Item("Milk", "Amul"), 10);
//        } catch (InventoryException e) {
//            System.err.println("Error occurred: " + e.getMessage());
//            if (e.getErrorCode() == InventoryException.ErrorCode.DUPLICATE_ITEM) {
//                System.err.println("Duplicate item detected. Skipping...");
//            }
//        }
    }

    // Helper method to print results
    private void printResults(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("No items found.");
        } else {
            for (Item item : items) {
                System.out.println(item.toString() + " " + service.getInventoryEntry(item).toString());
            }
        }
    }

}
