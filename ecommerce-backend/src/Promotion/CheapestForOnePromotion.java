package Promotion;

import java.util.*;
import Model.Product;

/**
 * Implementation of a promotion that applies the "Buy 3 products,
 * get the cheapest one for 1 zł" rule.
 */
public class CheapestForOnePromotion implements Promotion {

    /**
     * Applies the "Cheapest for 1 zł" promotion to the provided cart items.
     * Products cheaper than or equal to 1 zł are excluded from discount to avoid negative pricing.
     *
     * @param cartItems a map of products and their quantities
     * @return the total discount to subtract from the cart's full price
     */
    @Override
    public double apply(Map<Product, Integer> cartItems) {
        List<Double> prices = new ArrayList<>();

        // Flatten all product prices
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            double price = entry.getKey().getPrice();
            int quantity = entry.getValue();

            for (int i = 0; i < quantity; i++) {
                prices.add(price);
            }
        }

        // Sort ascending to pick the cheapest products per group
        Collections.sort(prices);

        int groupCount = prices.size() / 3;
        double discount = 0.0;

        for (int i = 0; i < groupCount; i++) {
            double cheapest = prices.get(i);

            // Only apply discount if price > 1 zl
            if (cheapest > 1.0) {
                discount += (cheapest - 1.0);
            }
        }

        return discount;
    }
}
