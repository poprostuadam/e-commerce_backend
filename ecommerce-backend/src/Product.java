import java.util.*;

/**
 * Represents a product in the store.
 */
public class Product {

    private String name;
    private double price;
    private Category category;
    private boolean isAvailable;

    /**
     * Constructor to initialize the product with its name, price, category, and availability.
     *
     * @param name        the name of the product
     * @param price       the price of the product
     * @param category    the category of the product
     * @param isAvailable the availability status of the product (true if available, false if unavailable)
     */
    public Product(String name, double price, Category category, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }
    // Getters

    /**
     * Get the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the category of the product
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Check if the product is available for purchase.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    // Setters

    /**
     * Set the name of the product.
     * @param name the name to set for the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the price of the product.
     * @param price the price to set for the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Set the category of the product
     * @param category the category to set for product
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /***
     * Set the availability status of the product.
     * @param isAvailable true if the product should be available, false if product should be unavailable
     */
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * Compares this product to another object for equality.
     *
     * @param o The object to compare this product to
     * @return true if the products are equal (same name, price, category, and availability), false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if the objects are the same
        if (o == null || getClass() != o.getClass()) return false; //Check if the object is of the same class
        Product product = (Product) o; // Cast the object to Product
        return Double.compare(product.price, price) == 0 &&
                isAvailable == product.isAvailable() &&
                Objects.equals(name, product.name) &&
                category == product.category;
    }

    /**
     * Generates a hash code for this product.
     *
     * @return A hash code value for the product based on its name, price, category, and availability.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price, category, isAvailable);
    }

    /***
     * Returns a string representation of the product
     * @return a string that includes the product's name, category, price and availability
     */
    @Override
    public String toString() {
/*        return String.format("%-12s | %-10s | %6.2f $ | %12s",
                name, category, price, isAvailable ? "Available" : "Unavailable");*/
        return name + " [" + category + "] " + price + "$ " + (isAvailable ? "[Available]" : "[Unavailable]");
    }
}
