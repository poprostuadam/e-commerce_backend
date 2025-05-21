package Promotion;

import Model.Product;
import java.util.Map;

/**
 * Represents a promotional discount that can be applied to the contents of a shopping cart.
 * <p>
 * Implementing classes define specific rules for calculating discounts based on the products
 * and their quantities in the cart.
 * </p>
 */

public interface Promotion {
    /**
     * Calculates the discount amount based on the provided cart contents.
     *
     * @param cartItems a map of products and their quantities in the shopping cart
     * @return the total discount to apply
     */
    double apply (Map<Product, Integer> cartItems);
}
