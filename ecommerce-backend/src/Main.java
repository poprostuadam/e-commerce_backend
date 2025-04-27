import Model.*;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        Product apple = new Product("Apple", 0.89, Category.FRUITS, true);
        Product beer = new Product("Beer", 1.15, Category.ALCOHOL, true);
        Product cheese = new Product("Cheese", 2.20, Category.DAIRY, true);
        Product fanta = new Product("Fanta", 0.99, Category.DRINKS, true);
        Product coke = new Product("Coke", 0.95, Category.DRINKS, true);
        Product water = new Product("Water", 0.49, Category.DRINKS, true);
        Product meat = new Product("Meat", 3.35, Category.MEAT, true);
        Product tomato = new Product("Tomato", 0.75, Category.VEGETABLES, true);
        Product salt = new Product("Salt", 0.29, Category.SPICES, true);
        Product pepper = new Product("Pepper", 0.19, Category.SPICES, false);

        List<Product> products = List.of(
                apple, beer, cheese, fanta, coke, water, meat, tomato, salt, pepper
        );

        IntStream.range(0, products.size())
                        .forEach(i -> catalog.addProduct(products.get(i), i+1));

        System.out.println(catalog);

        catalog.addProduct(apple, 10);
        catalog.decreaseProductQuantity(beer);
        catalog.decreaseProductQuantity(cheese);
        catalog.decreaseProductQuantity(beer);

        System.out.println(catalog);

        catalog.decreaseProductQuantity(beer);
        catalog.removeProduct(cheese);

        System.out.println("\n" + catalog);

        System.out.println("\nModel.Catalog sorted by name: ");
        catalog.displayCatalog(catalog.getProductsSortedByName());

        System.out.println("\nModel.Catalog sorted by price: ");
        catalog.displayCatalog(catalog.getProductsByCategorySortedByPrice(Category.SPICES, true, true));


        Cart cart = new Cart(catalog);
        cart.displayCart();
        cart.addProduct(apple);
        System.out.println(catalog.getProductQuantity(apple));
        cart.addProduct(apple);
        System.out.println(catalog.getProductQuantity(apple));
        cart.removeProduct(apple);
        System.out.println(catalog.getProductQuantity(apple));
        cart.addProduct(fanta);
        cart.addProduct(fanta);
        cart.addProduct(fanta);

        cart.displayCart();
        cart.displayTotalPrice();
        cart.finalizePurchase();
        cart.finalizePurchase();
        cart.displayCart();

    }
}