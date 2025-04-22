/**
 * Adapter class for adding products to the catalog with quantity
 */

public class CatalogItem   {

    private final Product product;
    private int quantity;

    public CatalogItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return product.isAvailable() && quantity > 0;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return product.getName() + " | " + product.getPrice() + " zl (" + quantity + " pcs.)";
    }
}
