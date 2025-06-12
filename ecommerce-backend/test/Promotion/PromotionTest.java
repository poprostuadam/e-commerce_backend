package Promotion;

import Model.Category;
import Model.Product;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionTest {

    private Product apple = new Product("Apple", 1.00, Category.FRUITS, true);
    private Product cheese = new Product("Cheese", 3.00, Category.DAIRY, true);

    @Test
    void tenPercentOff_shouldReturn10PercentDiscount() {
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(cheese, 2); // 6.00
        Promotion promo = new TenPercentOffPromotion();
        assertEquals(0.60, promo.apply(cart), 0.01);
    }

    @Test
    void buyOneGetSecondHalfPrice_shouldApplyCorrectDiscount() {
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(cheese, 3); // 1 pair = 1.50 off
        Promotion promo = new BuyOneGetSecondHalfPricePromotion();
        assertEquals(1.50, promo.apply(cart), 0.01);
    }

    @Test
    void cheapestForOne_shouldReduceCheapestEveryThree() {
        Map<Product, Integer> cart = new HashMap<>();
        cart.put(apple, 1);
        cart.put(cheese, 2); // cheapest is apple (1.00) → discount = 1.00 - 1.00 = 0
        Promotion promo = new CheapestForOnePromotion();
        assertEquals(0.00, promo.apply(cart), 0.01);
    }
}
