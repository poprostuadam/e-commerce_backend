import java.util.*;
import java.util.stream.Collectors;

public class Catalog {

    private final List<CatalogItem> entries = new ArrayList<>();

    public Catalog() {
        initializeCatalog();
    }

    // Catalog initialization
    private void initializeCatalog() {
        addProduct(new Product("Banana", 2.40, Category.FRUITS, true), 5);
        addProduct(new Product("Lemon", 1.50, Category.FRUITS, true), 2);
        addProduct(new Product("Apple", 1.09, Category.FRUITS, true), 10);
        addProduct(new Product("Bread", 0.89, Category.BAKERY, true), 4);
        addProduct(new Product("Salt", 0.29, Category.SPICES, true), 0);
        addProduct(new Product("Cola", 0.99, Category.DRINKS, true), 15);
        addProduct(new Product("Water", 0.49, Category.DRINKS, true), 1);
        addProduct(new Product("Milk", 0.79, Category.DAIRY, true), 5);
        addProduct(new Product("Beer", 1.15, Category.ALCOHOL, false), 10);
    }

    public void addProduct(Product product, int quantity) {
        entries.add(new CatalogItem(product, quantity));
    }

    public List<CatalogItem> getAllEntries() {
        return entries;
    }

    public List<Product> getProductsSortedByName() {
        return entries.stream()
                .map(CatalogItem::getProduct)
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategorySortedByPrice(Category category, boolean ascending, boolean showUnavailable) {
        return entries.stream()
                .map(CatalogItem::getProduct)
                .filter((Product p) -> p.getCategory() == category)
                .filter((Product p) -> showUnavailable || p.isAvailable())
                .sorted((Product p1, Product p2) -> {
                    /*
                    * p1.getPrice() < p2.getPrice() -> -1
                    * p1.getPrice() > p2.getPrice() -> 1
                    * p1.getPrice() = p2.getPrice() -> 0
                    * */
                    int compare = Double.compare(p1.getPrice(), p2.getPrice());
                    return ascending ? compare : -compare;
                })
                .collect(Collectors.toList());
    }

    public void clearCatalog() {
        entries.clear();
    }

}
