package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        // Verifies that all setter methods correctly modify internal fields
        // and that getter methods return updated values.
        Product p = new Product("Water", 0.99, Category.DRINKS, true);
        p.setName("Juice");
        p.setPrice(1.49);
        p.setAvailable(false);
        p.setCategory(Category.SNACKS);

        assertEquals("Juice", p.getName());
        assertEquals(1.49, p.getPrice(), 0.01);
        assertEquals(Category.SNACKS, p.getCategory());
        assertFalse(p.isAvailable());
    }

    @Test
    void equalsAndHashCode_shouldWorkForSameNameAndCategory() {
        // Verifies that two products with same name and category
        // are considered equal and have the same hashCode, even if other fields differ.
        Product p1 = new Product("A", 1.0, Category.MEAT, true);
        Product p2 = new Product("A", 1.0, Category.MEAT, false);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void toString_shouldReturnCorrectFormat() {
        // Verifies that the string representation of the product
        // follows the expected formatting with name, category, price and availability.
        Product p = new Product("Milk", 2.50, Category.DAIRY, true);
        String expected = "Milk [DAIRY] 2.5$ [Available]";
        assertEquals(expected, p.toString());
    }

    @Test
    void equals_shouldReturnFalseForDifferentName() {
        // Ensures that products with different names are not considered equal.
        Product p1 = new Product("Milk", 2.50, Category.DAIRY, true);
        Product p2 = new Product("Juice", 2.50, Category.DAIRY, true);
        assertNotEquals(p1, p2);
    }

    @Test
    void equals_shouldReturnFalseForDifferentCategory() {
        // Ensures that products with the same name but different categories are not equal.
        Product p1 = new Product("Milk", 2.50, Category.DAIRY, true);
        Product p2 = new Product("Milk", 2.50, Category.DRINKS, true);
        assertNotEquals(p1, p2);
    }

    @Test
    void equals_shouldReturnFalseForNullAndOtherType() {
        // Confirms that the equals method handles comparison with null and unrelated types correctly.
        Product p = new Product("Milk", 2.50, Category.DAIRY, true);
        assertNotEquals(null, p);
        assertNotEquals("some string", p);
    }

}