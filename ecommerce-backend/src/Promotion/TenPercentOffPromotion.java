package Promotion;

import java.util.*;
import Model.Product;

/**
 * Implementation of a promotion that applies a 10% discount to the total price of all products in the cart.
 */
public class TenPercentOffPromotion implements Promotion {

    /**
     * Applies the 10% discount promotion to the given cart.
     *
     * @param cartItems a map of products and their quantities
     * @return the total discount to subtract from the cart's full price
     */
    @Override
    public double apply(Map<Product, Integer> cartItems) {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total * 0.10;
    }
}
