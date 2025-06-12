package Model;

import Promotion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private Catalog catalog;
    private Cart cart;
    private Product apple;
    private Product cheese;

    @BeforeEach
    void setUp() {
        catalog = new Catalog();
        apple = new Product("Apple", 1.00, Category.FRUITS, true);
        cheese = new Product("Cheese", 3.00, Category.DAIRY, true);
        catalog.addProduct(apple, 10);
        catalog.addProduct(cheese, 5);
        cart = new Cart(catalog);
    }

    @Test
    void addProduct_shouldDecreaseCatalogQuantity() {
        cart.addProduct(apple);
        assertEquals(9, catalog.getProductQuantity(apple));
    }

    @Test
    void addProduct_unavailableProduct_shouldNotAdd() {
        apple.setAvailable(false);
        cart.addProduct(apple);
        assertEquals(10, catalog.getProductQuantity(apple));
    }

    @Test
    void removeProduct_shouldIncreaseCatalogQuantity() {
        cart.addProduct(apple);
        cart.removeProduct(apple);
        assertEquals(10, catalog.getProductQuantity(apple));
    }

    @Test
    void removeProduct_notInCart_shouldThrow() {
        assertThrows(NoSuchElementException.class, () -> cart.removeProduct(cheese));
    }

    @Test
    void calculateTotalPrice_noPromotion() {
        cart.addProduct(apple);
        cart.addProduct(cheese);
        assertEquals(4.00, cart.calculateTotalPrice(), 0.01);
    }

    @Test
    void calculateTotalPrice_withTenPercentOff() {
        cart.addProduct(apple);
        cart.addProduct(cheese);
        cart.applyPromotionCode("PROMO10");
        assertEquals(3.60, cart.calculateTotalPrice(), 0.01);
    }

    @Test
    void calculateTotalPrice_withBuyOneGetSecondHalfPrice() {
        cart.addProduct(cheese);
        cart.addProduct(cheese);
        cart.applyPromotionCode("BUY2HALF");
        assertEquals(4.50, cart.calculateTotalPrice(), 0.01); // 3.00 + 1.50
    }

    @Test
    void calculateTotalPrice_withCheapestForOne() {
        cart.addProduct(apple);
        cart.addProduct(cheese);
        cart.addProduct(cheese);
        cart.applyPromotionCode("CHEAPEST1PLN");
        assertEquals(7.00, cart.calculateTotalPrice(), 0.01); // Apple za 1 zł
    }

    @Test
    void finalizePurchase_shouldClearCart() {
        cart.addProduct(apple);
        cart.finalizePurchase();
        assertEquals(0.0, cart.calculateTotalPrice(), 0.01);
    }

    @Test
    void finalizePurchase_onEmptyCart_shouldNotCrash() {
        cart.finalizePurchase(); // just verify no exception
    }
}
