import java.util.*;
/**
 * Represents a shopping cart where products can be added and removed
 * */
public class Cart {
    private final Map<Product, Integer> cart;

    /**
     * Constructs a new Cart.
     */
    public Cart() {
        cart = new HashMap<>();
    }
}
