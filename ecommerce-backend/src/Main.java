
public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        System.out.println("All products (sorted by name)");
        catalog.getProductsSortedByName()
                .forEach((Product p) -> System.out.println(p.getName() + " - " + p.getPrice() + " zl"));

        System.out.println("\nAll products (sorted by name)");
        catalog.addProduct(new Product("Butter", 2.20, Category.DAIRY, true), 10);

        catalog.getProductsSortedByName()
                .forEach((Product p) -> System.out.println(p.getName() + " - " + p.getPrice() + " zl"));
    }
}