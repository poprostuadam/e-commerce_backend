import java.util.*;
import org.jetbrains.annotations.NotNull;

public class Main {

    static void printCatalog(@NotNull List<Product> products) {
        int[] idx = {1};

        System.out.println("--------------------------------------");
        products.forEach(product -> {
           System.out.println(idx[0] + ". " + product.getName() + " - "
           + product.getPrice() + " zl " + (product.isAvailable() ? "[Available]" : "[Unavailable]"));
           idx[0]++;
        });
    }

    public static void main(String[] args) {
        Catalog catalog = new Catalog();

    }
}