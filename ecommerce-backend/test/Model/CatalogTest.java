package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogTest {

    private Catalog catalog;
    private Product apple, cheese;

    @BeforeEach
    void setUp() {
        catalog = new Catalog();
        apple = new Product("Apple", 1.00, Category.FRUITS, true);
        cheese = new Product("Cheese", 3.00, Category.DAIRY, true);
        catalog.addProduct(apple, 5);
        catalog.addProduct(cheese, 2);
    }

    @Test
    void addProduct_shouldIncreaseQuantity() {
        catalog.addProduct(apple, 3);
        assertEquals(8, catalog.getProductQuantity(apple));
    }

    @Test
    void removeProduct_shouldDeleteProduct() {
        catalog.removeProduct(apple);
        assertEquals(0, catalog.getProductQuantity(apple));
    }

    @Test
    void removeProduct_notInCatalog_shouldThrow() {
        Product fake = new Product("X", 1.0, Category.SNACKS, true);
        assertThrows(NoSuchElementException.class, () -> catalog.removeProduct(fake));
    }

    @Test
    void decreaseProductQuantity_shouldReduceAndDisableIfZero() {
        catalog.decreaseProductQuantity(cheese);
        catalog.decreaseProductQuantity(cheese);
        assertEquals(0, catalog.getProductQuantity(cheese));
        assertFalse(cheese.isAvailable());
    }

    @Test
    void getProductsSortedByName_shouldReturnAlphabeticalOrder() {
        List<Product> result = catalog.getProductsSortedByName();
        assertEquals("Apple", result.get(0).getName());
    }

    @Test
    void getProductsByCategorySortedByPrice_shouldFilterAndSort() {
        Product x = new Product("X", 0.5, Category.FRUITS, true);
        catalog.addProduct(x, 2);
        List<Product> result = catalog.getProductsByCategorySortedByPrice(Category.FRUITS, true, true);
        assertEquals("X", result.get(0).getName());
    }
}
