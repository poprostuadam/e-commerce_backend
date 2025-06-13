package Model;

import Promotion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test suite for the Cart class.
 * Tests adding and removing products, calculating prices, and handling promotions.
 */
public class CartTest {

    private Catalog catalog;
    private Cart cart;
    private Product apple;
    private Product cheese;

    /**
     * Sets up the catalog and cart before each test.
     */
    @BeforeEach
    void setUp() {
        catalog = new Catalog();
        apple = new Product("Apple", 1.00, Category.FRUITS, true);
        cheese = new Product("Cheese", 3.00, Category.DAIRY, true);
        catalog.addProduct(apple, 10);
        catalog.addProduct(cheese, 5);
        cart = new Cart(catalog);
    }

    private void addProductMultipleTimes(Product product, int times) {
        for (int i = 0; i < times; i++) {
            cart.addProduct(product);
        }
    }

    private void assertTotalPrice(double expectedPrice) {
        assertEquals(expectedPrice, cart.calculateTotalPrice(), 0.01);
    }

    /**
     * Adding a product should decrease its quantity in the catalog by 1.
     */
    @Test
    void addProduct_shouldDecreaseCatalogQuantity() {
        cart.addProduct(apple);
        assertEquals(9, catalog.getProductQuantity(apple));
    }

    /**
     * An unavailable product (available = false) should not be added to the cart.
     */
    @Test
    void addProduct_unavailableProduct_shouldNotAdd() {
        apple.setAvailable(false);
        cart.addProduct(apple);
        assertEquals(10, catalog.getProductQuantity(apple)); // no change
    }

    /**
     * Removing a product from the cart should restore its quantity in the catalog.
     */
    @Test
    void removeProduct_shouldIncreaseCatalogQuantity() {
        cart.addProduct(apple);
        cart.removeProduct(apple);
        assertEquals(10, catalog.getProductQuantity(apple));
    }

    /**
     * Trying to remove a product not in the cart should throw an exception.
     */
    @Test
    void removeProduct_notInCart_shouldThrow() {
        assertThrows(NoSuchElementException.class, () -> cart.removeProduct(cheese));
    }

    /**
     * Final price without promotions should be the sum of product prices.
     */
    @Test
    void calculateTotalPrice_noPromotion() {
        cart.addProduct(apple);
        cart.addProduct(cheese);
        assertTotalPrice(4.00);
    }

    /**
     * A 10% discount promotion should lower the total price.
     */
    @Test
    void calculateTotalPrice_withTenPercentOff() {
        cart.addProduct(apple);
        cart.addProduct(cheese);
        cart.applyPromotionCode("PROMO10");
        assertTotalPrice(3.60);
    }

    /**
     * "Buy one, get the second at half price" promotion – for identical products.
     */
    @Test
    void calculateTotalPrice_withBuyOneGetSecondHalfPrice() {
        addProductMultipleTimes(cheese, 2);
        cart.applyPromotionCode("BUY2HALF");
        assertTotalPrice(4.50); // 3.00 + 1.50
    }

    /**
     * Cheapest product for 1 PLN.
     */
    @Test
    void calculateTotalPrice_withCheapestForOne() {
        cart.addProduct(apple);
        addProductMultipleTimes(cheese, 2);
        cart.applyPromotionCode("CHEAPEST1PLN");
        assertTotalPrice(7.00); // Apple for 1.00 + 3.00 * 2
    }

    /**
     * Finalizing the purchase should clear the cart.
     */
    @Test
    void finalizePurchase_shouldClearCart() {
        cart.addProduct(apple);
        cart.finalizePurchase();
        assertTotalPrice(0.0);
    }

    /**
     * Finalizing an empty cart – should not throw an exception.
     */
    @Test
    void finalizePurchase_onEmptyCart_shouldNotCrash() {
        cart.finalizePurchase(); // should not crash
    }

    /**
     * Finalizing a purchase with promotion should apply it before clearing the cart.
     */
    @Test
    void finalizePurchase_withPromotion_shouldApplyCorrectly() {
        cart.addProduct(apple);
        cart.addProduct(cheese);
        cart.applyPromotionCode("PROMO10");
        cart.finalizePurchase(); // apply discount and clear
        assertTotalPrice(0.0); // cart is empty after purchase
    }

    @Test
    void applyPromotionCode_withEmptyString_shouldNotApplyPromotion() {
        // Verifies that an empty promotion code results in no active promotion
        cart.addProduct(apple); // 1.00
        cart.addProduct(apple); // 2.00 total

        cart.applyPromotionCode(""); // No promotion should be applied

        double price = cart.calculateTotalPrice();
        assertEquals(2.00, price, 0.01); // Should be full price
    }

    @Test
    void wrongPromotionCode_shouldNotApplyPromotion() {
        // Verifies that a wrong promotion code results in no active promotion
        cart.addProduct(apple); // 1.00
        cart.addProduct(apple); // 2.00 total

        cart.applyPromotionCode("wrong_code"); // No promotion should be applied

        double price = cart.calculateTotalPrice();
        assertEquals(2.00, price, 0.01); // Should be full price
    }
}
