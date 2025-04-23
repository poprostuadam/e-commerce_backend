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
        Product pepper = new Product("Pepper", 0.29, Category.SPICES, false);

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
        catalog.removeProduct(cheese);

        System.out.println(catalog);

        System.out.println("\nCatalog sorted by name: ");
        catalog.getProductsSortedByName()
                .forEach(product -> {
                    System.out.println(product + " | Quantity: " + catalog.getProductQuantity(product));
                });

        System.out.println("\nCatalog sorted by price: ");
        catalog.getProductsByCategorySortedByPrice(Category.SPICES, true, true)
                .forEach(product -> {
                    System.out.println(product + " | Quantity: " + catalog.getProductQuantity(product));
                });
    }
}