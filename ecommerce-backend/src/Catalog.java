import java.util.*;
import java.util.stream.Collectors;

/***
 * Represents catalog.
 */
public class Catalog {
    private final  Map<Product, Integer> catalog;

    /**
     * Constructs a new Catalog.
     */
    public Catalog() {
        catalog = new HashMap<>();
    }

    /**
     * Adds a specific quantity of a product to the catalog.
     *
     * @param product The product to be added
     * @param quantity The quantity of the product to be added
     */
    public void addProduct(Product product, int quantity) {
        if (catalog.containsKey(product)) {
            catalog.put(product, catalog.get(product) + quantity);
        } else {
            catalog.put(product, quantity);
        }
    }

    public void removeProduct(Product product) {
        if (catalog.containsKey(product)) {
            catalog.remove(product);
            System.out.println("Product " + product.getName() + " removed");
        } else {
            System.out.println("Product " + product.getName() + " not found");
        }
    }

    /**
     * Decreases the quantity of a product in the catalog.
     *
     * @param product The product whose quantity will be decreased
     */
    public void decreaseProductQuantity(Product product) {
        if (catalog.containsKey(product)) {
            int quantity = catalog.get(product);
            if (quantity > 1) {
                catalog.put(product, quantity - 1);
                System.out.println("Product " + product.getName() + " has been decreased from " + quantity + " to: " + (quantity - 1));
            } else {
                System.out.println("Product " + product.getName() + " quantity is already 0.");
                removeProduct(product);
            }
        } else {
            System.out.println("Product " + product.getName() + " does not exist in catalog.");
        }
    }

    /**
     * Set quantity of a product in the catalog.
     * @param product The product whose quantity will be set
     * @param quantity The quantity of the product to be set
     */
    public void setProductQuantity(Product product, int quantity) {
            catalog.put(product, quantity);
    }

    /**
     * Get the product quantity.
     *
     * @param product The product whose quantity will be returned
     */
    public int getProductQuantity(Product product) {
        if (catalog.containsKey(product)) {
            return catalog.get(product);
        } else {
            System.out.println("Product " + product.getName() + " does not exist in catalog.");
            return 0;
        }
    }

    /**
     * Returns a list of all products sorted by their name in ascending order.
     *
     * @return a list of products sorted by name
     */
    public List<Product> getProductsSortedByName() {
        return catalog.keySet().stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of products filtered by the specified category and availability,
     * sorted by price in either ascending or descending order.
     *
     * @param category the category to filter products by
     * @param ascending whether to sort the products in ascending (true) or descending (false) order of price
     * @param showUnavailable whether to include unavailable products in the result (true to include, false to exclude)
     * @return a list of products filtered by category and sorted by price
     */
    public List<Product> getProductsByCategorySortedByPrice(Category category, boolean ascending, boolean showUnavailable) {
        // Filter products by category, availability, and sort by price
        return catalog.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == category)  // Filter by category
                .filter(entry -> showUnavailable || entry.getKey().isAvailable())  // Filter by availability
                .sorted((entry1, entry2) -> {
                    // Compare product prices
                    int compare = Double.compare(entry1.getKey().getPrice(), entry2.getKey().getPrice());
                    return ascending ? compare : -compare;
                })
                .map(Map.Entry::getKey)  // Return only the products (keys)
                .collect(Collectors.toList());
    }

    /**
     * Returns a string representation of the catalog, listing all products along with their details and quantities.
     * If the catalog is empty, a message indicating this will be returned instead.
     *
     * @return a string representation of the catalog's contents
     */
    @Override
    public String toString() {
        StringBuilder sbuilder = new StringBuilder("Catalog:\n");
        for (Map.Entry<Product, Integer> entry : catalog.entrySet()) {
            sbuilder.append("- ")
                    .append(entry.getKey().getName())
                    .append("\t(Category: ")
                    .append(entry.getKey().getCategory())
                    .append(" | Price: ")
                    .append(String.format("%.2f", entry.getKey().getPrice()) + "$")
                    .append(" | Available: ")
                    .append(entry.getKey().isAvailable() ? "YES" : "NO")
                    .append(") Quantity: ")
                    .append(entry.getValue())
                    .append("\n");
        }

        if (catalog.isEmpty()) {
            sbuilder.append("Catalog is empty\n");
        }
        return sbuilder.toString();
    }
}
