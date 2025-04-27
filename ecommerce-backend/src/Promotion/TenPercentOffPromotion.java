package Promotion;

import Model.Product;
import java.util.*;

public class TenPercentOffPromotion implements Promotion {
    @Override
    public double apply(Map<Product, Integer> cartItems) {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total * 0.10;
    }
}
