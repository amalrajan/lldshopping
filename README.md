# LLD - Inventory Management


### Add Item to Inventory
- Adds an item with its category, brand, and price.
- Syntax: 
```java
addItem(String category, String brand, double price);
```

### Search Items Using Filters
Supports searching based on:
- Brand: Search by item brand.
- Category: Search by item category.
- Price Range: Search by items within a price range.

Or a combination of multiple filters.
Syntax:
```java
searchItems(Map<FilterKey, List<String>> filters, OrderBy orderBy, OrderDir orderDir);
```

OrderBy (Optional, defaults to price):
- PRICE - Order by price.
- QUANTITY - Order by quantity.

OrderDir (Optional, defaults to ascending):
- ASC - Ascending order.
- DESC - Descending order.

### Extensible Filter Support

Dynamically applies multiple filters.
Adding new filters requires minimal changes.

### Dao
Inventory is stored in a map:
Item<Category, Brand> -> InventoryEntry<Price, Quantity>

