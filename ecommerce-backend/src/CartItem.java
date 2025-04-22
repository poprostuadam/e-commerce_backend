/**
 * Represents a single item in the shopping cart.
 * Stores the product and its quantity.
 * */

public class CartItem {
    private final Product product;
    private int quantity;

    /**
     * Creates a CartItem with specified product and quantity.
     * @param product the product
     * @param quantity how many times the product is added
     * */
    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to decrease cannot be negative");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity -= amount;
    }
}
