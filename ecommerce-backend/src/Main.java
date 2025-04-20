import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Locale;

public class Main {

    static void printCatalog(@NotNull List<Product> products) {
        int[] idx = {1};

        products.forEach(product -> {
           System.out.println(idx[0] + ". " + product.getName() + " - "
           + product.getPrice() + " zl " + (product.isAvailable() ? "[Available]" : "[Unavailable]"));
           idx[0]++;
        });
    }

    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        System.out.println("--------------------------------------");
        System.out.println("All products (sorted by name):");
        printCatalog(catalog.getProductsSortedByName());

        catalog.addProduct(new Product("Butter", 2.20, Category.DAIRY, true), 10);

        System.out.println("--------------------------------------");
        System.out.println("All products (sorted by name):");
        printCatalog(catalog.getProductsSortedByName());

        System.out.println("--------------------------------------");
        System.out.println("All products (sorted by price asc.):");
        printCatalog(catalog.getProductsByCategorySortedByPrice(Category.FRUITS, false, true));

    }
}