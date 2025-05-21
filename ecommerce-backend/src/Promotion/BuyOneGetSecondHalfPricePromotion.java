package Promotion;

import java.util.Map;
import Model.Product;

/**
 * Implementation of a promotion that applies "Buy one, get the second of the same product at half price".
 */
public class BuyOneGetSecondHalfPricePromotion implements Promotion {

    /**
     * Applies the "Second same product for half price" promotion.
     * Products with extremely low prices (e.g. < 0.01 zł) are excluded to avoid rounding issues or abuse.
     *
     * @param cartItems a map of products and their quantities
     * @return the total discount to subtract from the cart's full price
     */
    @Override
    public double apply(Map<Product, Integer> cartItems) {
        double discount = 0.0;

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            double price = entry.getKey().getPrice();

            if (price < 0.01) {
                continue; // skip very cheap products
            }

            int quantity = entry.getValue();
            int discountedPairs = quantity / 2;

            discount += discountedPairs * (price / 2.0);
        }

        return discount;
    }
}
