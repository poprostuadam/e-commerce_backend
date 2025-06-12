package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Catalog class.
 * Verifies product addition, removal, quantity updates,
 * as well as filtering and sorting functionalities.
 */
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

    /**
     * Adding a product should increase its quantity in the catalog.
     */
    @Test
    void addProduct_shouldIncreaseQuantity() {
        catalog.addProduct(apple, 3);
        assertEquals(8, catalog.getProductQuantity(apple));
    }

    /**
     * Removing a product should set its quantity to 0.
     */
    @Test
    void removeProduct_shouldDeleteProduct() {
        catalog.removeProduct(apple);
        assertEquals(0, catalog.getProductQuantity(apple));
    }

    /**
     * Trying to remove a non-existing product should throw an exception.
     */
    @Test
    void removeProduct_notInCatalog_shouldThrow() {
        Product fake = new Product("X", 1.0, Category.SNACKS, true);
        assertThrows(NoSuchElementException.class, () -> catalog.removeProduct(fake));
    }

    /**
     * Decreasing product quantity to 0 should make it unavailable.
     */
    @Test
    void decreaseProductQuantity_shouldReduceAndDisableIfZero() {
        catalog.decreaseProductQuantity(cheese);
        catalog.decreaseProductQuantity(cheese);
        assertEquals(0, catalog.getProductQuantity(cheese));
        assertFalse(cheese.isAvailable());
    }

    /**
     * Decreasing quantity of a product already at 0 should not throw,
     * only print a warning.
     */
    @Test
    void decreaseProductQuantity_whenOutOfStock_shouldPrintWarning() {
        catalog.setProductQuantity(apple, 0);
        catalog.decreaseProductQuantity(apple);  // should warn but not throw
        assertEquals(0, catalog.getProductQuantity(apple));
        assertFalse(apple.isAvailable()); // availability does not change here
    }

    /**
     * Products should be returned sorted alphabetically by name.
     */
    @Test
    void getProductsSortedByName_shouldReturnAlphabeticalOrder() {
        List<Product> result = catalog.getProductsSortedByName();
        assertEquals("Apple", result.get(0).getName());
    }

    /**
     * Products in a given category should be filtered and sorted by ascending price.
     */
    @Test
    void getProductsByCategorySortedByPrice_shouldFilterAndSort() {
        Product x = new Product("X", 0.5, Category.FRUITS, true);
        catalog.addProduct(x, 2);
        List<Product> result = catalog.getProductsByCategorySortedByPrice(Category.FRUITS, true, true);
        assertEquals("X", result.get(0).getName());
    }

    /**
     * Unavailable products should be excluded if the onlyAvailable flag is true.
     */
    @Test
    void getProductsByCategorySortedByPrice_shouldExcludeUnavailableIfRequired() {
        apple.setAvailable(false);
        List<Product> result = catalog.getProductsByCategorySortedByPrice(Category.FRUITS, true, false);
        assertTrue(result.isEmpty());
    }

    /**
     * Setting product quantity should overwrite the existing value.
     */
    @Test
    void setProductQuantity_shouldOverwriteQuantity() {
        catalog.setProductQuantity(cheese, 10);
        assertEquals(10, catalog.getProductQuantity(cheese));
    }

    /**
     * Getting quantity of a product not in the catalog should return 0.
     */
    @Test
    void getProductQuantity_whenNotFound_shouldReturnZero() {
        Product unknown = new Product("Unknown", 1.0, Category.SNACKS, true);
        assertEquals(0, catalog.getProductQuantity(unknown));
    }

    /**
     * The toString method should contain product details.
     */
    @Test
    void toString_shouldContainProductDetails() {
        String output = catalog.toString();
        assertTrue(output.contains("Apple"));
        assertTrue(output.contains("Cheese"));
        assertTrue(output.contains("Quantity:"));
    }

    /**
     * An empty catalog should include an informative message in toString.
     */
    @Test
    void toString_whenEmpty_shouldIndicateEmptyCatalog() {
        Catalog emptyCatalog = new Catalog();
        assertTrue(emptyCatalog.toString().contains("Catalog is empty"));
    }

    // 🆕 ADDITIONAL TESTS:

    /**
     * Adding a product with quantity 0 should still add it,
     * but quantity should remain 0.
     */
    @Test
    void addProductWithZeroQuantity_shouldAddButUnavailable() {
        Product banana = new Product("Banana", 2.0, Category.FRUITS, true);
        catalog.addProduct(banana, 0);
        assertEquals(0, catalog.getProductQuantity(banana));
    }


    @Test
    void setProductQuantity_shouldSetPositiveQuantityAndMarkAvailable() {
        // Verifies that setting a positive quantity updates the catalog and sets product as available
        catalog.setProductQuantity(apple, 7);
        assertEquals(7, catalog.getProductQuantity(apple));
        assertTrue(apple.isAvailable());
    }

    @Test
    void setProductQuantity_shouldSetZeroAndMarkUnavailable() {
        // Verifies that setting quantity to 0 disables product availability
        catalog.setProductQuantity(apple, 0);
        assertEquals(0, catalog.getProductQuantity(apple));
        assertFalse(apple.isAvailable());
    }

    @Test
    void setProductQuantity_shouldThrowForNegativeQuantity() {
        // Verifies that setting a negative quantity throws an exception
        assertThrows(IllegalArgumentException.class, () -> catalog.setProductQuantity(apple, -5));
    }


    /**
     * Products with the same price should still be sorted properly by price.
     */
    @Test
    void getProductsByCategorySortedByPrice_shouldSortByPriceEvenIfEqual() {
        Product anotherCheese = new Product("Z-Cheese", 3.00, Category.DAIRY, true);
        catalog.addProduct(anotherCheese, 1);
        List<Product> result = catalog.getProductsByCategorySortedByPrice(Category.DAIRY, true, true);
        assertEquals(2, result.size());
        assertTrue(result.get(0).getPrice() <= result.get(1).getPrice());
    }
}
