package Promotion;

import Model.Product;
import java.util.Map;

public interface Promotion {
    double apply (Map<Product, Integer> cartItems);
}
