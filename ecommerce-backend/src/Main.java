import Model.*;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Create the product catalog
        Catalog catalog = new Catalog();

        // Define products
        Product apple = new Product("Apple", 0.89, Category.FRUITS, true);
        Product beer = new Product("Beer", 1.15, Category.ALCOHOL, true);
        Product cheese = new Product("Cheese", 2.20, Category.DAIRY, true);
        Product fanta = new Product("Fanta", 0.99, Category.DRINKS, true);
        Product coke = new Product("Coke", 0.95, Category.DRINKS, true);
        Product water = new Product("Water", 0.49, Category.DRINKS, true);
        Product meat = new Product("Meat", 3.35, Category.MEAT, true);
        Product tomato = new Product("Tomato", 0.75, Category.VEGETABLES, true);
        Product salt = new Product("Salt", 0.29, Category.SPICES, true);
        Product pepper = new Product("Pepper", 0.19, Category.SPICES, false); // unavailable

        // Add products to catalog with increasing quantities
        List<Product> products = List.of(
                apple, beer, cheese, fanta, coke, water, meat, tomato, salt, pepper
        );
        IntStream.range(0, products.size())
                .forEach(i -> catalog.addProduct(products.get(i), i + 1));

        // Display initial catalog
        System.out.println(catalog);

        // Update product quantities in catalog
        catalog.addProduct(apple, 10);              // add more apples
        catalog.decreaseProductQuantity(coke);      // simulate purchase
        catalog.decreaseProductQuantity(cheese);
        catalog.decreaseProductQuantity(coke);

        System.out.println(catalog);

        catalog.decreaseProductQuantity(coke);      // beer stock should be lower
        catalog.removeProduct(tomato);              // remove cheese entirely

        catalog.addProduct(cheese, 8);
        catalog.addProduct(beer, 8);

        // Display catalog after removals
        System.out.println("\n" + catalog);

        // Display catalog sorted by name
        System.out.println("\nModel.Catalog sorted by name: ");
        catalog.displayCatalog(catalog.getProductsSortedByName());

        // Display catalog sorted by price in SPICES category
        System.out.println("\nModel.Catalog sorted by price: ");
        catalog.displayCatalog(catalog.getProductsByCategorySortedByPrice(Category.SPICES, true, true));

        // Create a new cart
        Cart cart = new Cart(catalog);

        // Try displaying an empty cart
        cart.displayCart();

        // Add and remove products from cart
        cart.addProduct(apple);
        System.out.println("Apple stock after add: " + catalog.getProductQuantity(apple));
        cart.addProduct(apple);
        System.out.println("Apple stock after second add: " + catalog.getProductQuantity(apple));
        cart.removeProduct(apple);
        System.out.println("Apple stock after remove: " + catalog.getProductQuantity(apple));

        cart.addProduct(meat);
        cart.addProduct(beer);
        cart.addProduct(water);

        // Show cart contents and total before applying promotion
        cart.displayCart();
        cart.displayTotalPrice();

        // Apply promotion code: 10% off
        cart.applyPromotionCode("PROMO10");
        cart.displayTotalPrice();

        // Finalize purchase and show total after discount
        cart.finalizePurchase();

        //-----------------------------------------------------------

        cart.addProduct(fanta);
        cart.addProduct(fanta);

        cart.displayCart();
        cart.displayTotalPrice();

        // Apply promotion code: Buy one get second half price
        cart.applyPromotionCode("BUY2HALF");
        cart.displayTotalPrice();

        // Finalize purchase and show total after discount
        cart.finalizePurchase();

        //-----------------------------------------------------------

        cart.addProduct(cheese);
        cart.addProduct(cheese);
        cart.addProduct(cheese);
        cart.addProduct(meat);
        cart.addProduct(meat);
        cart.addProduct(meat);
        cart.addProduct(beer);

        cart.displayCart();
        cart.displayTotalPrice();

        // Apply promotion code: Cheapest item for 1 PLN
        cart.applyPromotionCode("CHEAPEST1PLN");
        cart.displayTotalPrice();

        // Try finalizing again (cart is already empty)
        cart.finalizePurchase();

        // Display the now-empty cart
        cart.displayCart();
    }
}