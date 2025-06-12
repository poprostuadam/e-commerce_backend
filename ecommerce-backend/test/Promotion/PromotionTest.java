package Promotion;

import Model.Category;
import Model.Product;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionTest {

    private final Product apple = new Product("Apple", 1.00, Category.FRUITS, true);
    private final Product cheese = new Product("Cheese", 3.00, Category.DAIRY, true);
    private final Product banana = new Product("Banana", 1.50, Category.FRUITS, true);
    private final Product salt = new Product("Salt", 0.005, Category.SPICES, true);

    // ───────────────────────────────────────
    // TenPercentOffPromotion
    // ───────────────────────────────────────

    @Test
    void tenPercentOff_shouldApplyDiscountToTotalPrice() {
        // Verifies that 10% discount is applied correctly to the total value
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(cheese, 2); // 6.00 → 0.60 discount
        Promotion promo = new TenPercentOffPromotion();
        assertEquals(0.60, promo.apply(cart), 0.01);
    }

    @Test
    void tenPercentOff_shouldReturnZeroForEmptyCart() {
        // Verifies that discount is zero when cart is empty
        Map<Product, Integer> cart = new HashMap<>();
        Promotion promo = new TenPercentOffPromotion();
        assertEquals(0.0, promo.apply(cart), 0.01);
    }

    @Test
    void tenPercentOff_shouldHandleMixedProducts() {
        // Verifies correct discount for mixed product values
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(apple, 2);  // 2.00
        cart.put(cheese, 1); // 3.00 → Total 5.00 → Discount 0.50
        Promotion promo = new TenPercentOffPromotion();
        assertEquals(0.50, promo.apply(cart), 0.01);
    }

    // ───────────────────────────────────────
    // BuyOneGetSecondHalfPricePromotion
    // ───────────────────────────────────────

    @Test
    void buyOneGetSecondHalf_shouldApplyForEvenQuantity() {
        // Applies 50% discount on second item of the same product
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(cheese, 4); // Two pairs → 3.00 discount (2 * 1.50)
        Promotion promo = new BuyOneGetSecondHalfPricePromotion();
        assertEquals(3.00, promo.apply(cart), 0.01);
    }

    @Test
    void buyOneGetSecondHalf_shouldIgnoreUnpairedItem() {
        // Verifies that unpaired third item doesn't get discount
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(cheese, 3); // 1 pair → 1.50 discount, 1 full price
        Promotion promo = new BuyOneGetSecondHalfPricePromotion();
        assertEquals(1.50, promo.apply(cart), 0.01);
    }

    @Test
    void buyOneGetSecondHalf_shouldReturnZeroForSingleItem() {
        // No discount if there's only one item
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(cheese, 1); // no pair → 0 discount
        Promotion promo = new BuyOneGetSecondHalfPricePromotion();
        assertEquals(0.0, promo.apply(cart), 0.01);
    }

    @Test
    void buyOneGetSecondHalf_shouldIgnoreVeryCheapProducts() {
        // Products priced < 0.01 are excluded to avoid rounding abuse
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(salt, 10); // Should be ignored
        Promotion promo = new BuyOneGetSecondHalfPricePromotion();
        assertEquals(0.0, promo.apply(cart), 0.01);
    }

    @Test
    void buyOneGetSecondHalf_shouldApplyToMultipleProducts() {
        // Applies discount separately per product type
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(cheese, 2); // 1 pair → 1.50
        cart.put(banana, 2); // 1 pair → 0.75 → Total 2.25
        Promotion promo = new BuyOneGetSecondHalfPricePromotion();
        assertEquals(2.25, promo.apply(cart), 0.01);
    }

    // ───────────────────────────────────────
    // CheapestForOnePromotion
    // ───────────────────────────────────────

    @Test
    void cheapestForOne_shouldApplyIfCheapestAboveOneZl() {
        // If cheapest product costs > 1 zł, it gets discounted to 1 zł per group of 3
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(banana, 3); // [1.50, 1.50, 1.50] → one 0.50 discount
        Promotion promo = new CheapestForOnePromotion();
        assertEquals(0.50, promo.apply(cart), 0.01);
    }

    @Test
    void cheapestForOne_shouldSkipCheapestIfPriceIsOneOrLess() {
        // Product priced ≤ 1.00 doesn't get discounted
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(apple, 1);
        cart.put(cheese, 2); // [1.00, 3.00, 3.00] → apple skipped → 0.00
        Promotion promo = new CheapestForOnePromotion();
        assertEquals(0.00, promo.apply(cart), 0.01);
    }

    @Test
    void cheapestForOne_shouldApplyForMultipleGroups() {
        // Applies discount for every group of 3
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(banana, 6); // Two groups of [1.50, 1.50, 1.50] → 2 x 0.50
        Promotion promo = new CheapestForOnePromotion();
        assertEquals(1.00, promo.apply(cart), 0.01);
    }

    @Test
    void cheapestForOne_shouldIgnoreRemainingItemsIfLessThanThree() {
        // Only full groups of 3 count
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(cheese, 2); // Not enough for a group
        Promotion promo = new CheapestForOnePromotion();
        assertEquals(0.0, promo.apply(cart), 0.01);
    }

    @Test
    void cheapestForOne_shouldHandleEmptyCart() {
        // No items → no discount
        Map<Product, Integer> cart = new HashMap<>();
        Promotion promo = new CheapestForOnePromotion();
        assertEquals(0.0, promo.apply(cart), 0.01);
    }
}