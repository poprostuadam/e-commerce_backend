package Model;

import Promotion.*;

import java.util.*;
/**
 * Represents a shopping cart where products can be added and removed.
 * It also handles product availability, quantity management, and total price calculations,
 * along with applying promotions and finalizing purchases.
 */
public class Cart {
    private final Catalog catalog;
    private final Map<Product, Integer> cart;

    private Promotion activePromotion;

    /**
     * Constructs a new Model.Cart.
     *
     * @param catalog the catalog to interact with
     */
    public Cart(Catalog catalog) {
        this.catalog = catalog;
        cart = new HashMap<>();
    }

    /**
     * Adds a product to the cart.
     * Decreases the quantity in the catalog.
     *
     * @param product the product to add
     */
    public void addProduct(Product product) {
        boolean available = product.isAvailable();

        if (available) {
            cart.put(product, cart.getOrDefault(product, 0) + 1);
            catalog.decreaseProductQuantity(product);
            System.out.println("Added " + product.getName() + " to the cart.");
        } else {
            System.out.println("Model.Product " + product.getName() + " not found in the catalog.");
        }
    }


    /**
     * Removes a product from the cart.
     * Increases the quantity back in the catalog.
     *
     * @param product the product to remove
     */
    public void removeProduct(Product product) {
        if (cart.containsKey(product)) {
            int quantity = cart.get(product);

            if (quantity > 1) {
                cart.put(product, quantity - 1);
            } else {
                cart.remove(product);
            }

            catalog.setProductQuantity(product, catalog.getProductQuantity(product) + 1);
            product.setAvailable(true);

            System.out.println("Removed " + product.getName() + " from cart.");
        } else {
//            System.out.println("Model.Product " + product.getName() + " is not in the cart.");
            throw new NoSuchElementException("Model.Product " + product.getName() + " not found in the cart.");
        }
    }

    /**
     * Calculates the total price of the cart.
     * If a promotion is applied, it will adjust the total price accordingly.
     *
     * @return the total price of all products in the cart after applying any active promotions
     */
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }

        if (activePromotion != null) {
            double discount = activePromotion.apply(cart);
            totalPrice -= discount;
        }

        return totalPrice;
    }

    /**
     * Displays the total price of the cart, including any applicable promotions.
     */
    public void displayTotalPrice() {
        double totalPrice = calculateTotalPrice();
        System.out.printf("Total price: %.2fzl\n", totalPrice);
    }

    /**
     * Displays all products in the cart along with their quantities and prices.
     */
    public void displayCart() {
        StringBuilder sbuilder = new StringBuilder("Model.Cart:\n");
        if (cart.isEmpty()) {
            sbuilder.append("Model.Cart is empty\n");
        } else {
            sbuilder.append(String.format("| %-20s | %-10s | %-10s | %-8s |\n", "Model.Product", "Price", "Quantity", "Total"));
            sbuilder.append("|----------------------|------------|------------|----------|\n");
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                sbuilder.append(String.format(
                        "| %-20s | %-10s | %-10s | %-8s |\n",
                        entry.getKey().getName(),
                        String.format("%.2fzl", entry.getKey().getPrice()),
                        entry.getValue(),
                        String.format("%.2fzl", entry.getKey().getPrice() * entry.getValue())
                ));
            }
        }
        System.out.println(sbuilder.toString());
    }

    /**
     * Applies a promotion code to the cart.
     * The available promotions are hardcoded (e.g., 10% off).
     *
     * @param code the promotion code to apply
     */
    public void applyPromotionCode(String code) {

        if (code == null || code.isBlank()) {
            activePromotion = null;
            System.out.println("No promotion applied.");
            return;
        }

        switch (code) {
            case "PROMO10":
                activePromotion = new TenPercentOffPromotion();
                System.out.println("Activated 10% off promotion.");
                break;
            case "CHEAPEST1PLN":
                activePromotion = new CheapestForOnePromotion();
                System.out.println("Activated cheapest product for 1 zl promotion.");
                break;
            case "BUY2HALF":
                activePromotion = new BuyOneGetSecondHalfPricePromotion();
                System.out.println("Activated buy 1 get second half price promotion.");
                break;
            default:
                System.out.println("Invalid promotion code.");
                break;
        }
    }


    /**
     * Finalizes the purchase by displaying the cart contents and total price,
     * then clearing the cart.
     * <p>
     * If the cart is empty, a message is displayed and no further actions are performed.
     * </p>
     */
    public void finalizePurchase() {
        if (cart.isEmpty()) {
            System.out.println("Model.Cart is empty. Nothing to finalize.");
            return;
        }

        System.out.println("Finalizing purchase ... ");
        displayCart();
        displayTotalPrice();
        cart.clear();
        activePromotion = null;
        System.out.println("Purchase completed.\nModel.Cart has been emptied");
    }

}
