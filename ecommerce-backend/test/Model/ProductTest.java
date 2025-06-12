package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
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
        Product p1 = new Product("A", 1.0, Category.MEAT, true);
        Product p2 = new Product("A", 1.0, Category.MEAT, false);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}
