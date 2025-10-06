package phase1_teams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PileDriverTest {
    private PileDriver driver;

    @BeforeEach
    void setUp() {
        driver = new PileDriver();
    }

    @Test
    void push() {
        assertTrue(driver.isEmpty());
        driver.push("A");
        driver.push("B");

        assertEquals(2, driver.size());
        assertEquals("B", driver.pop());
        assertEquals(1, driver.size());
        assertEquals("A", driver.pop());
        assertTrue(driver.isEmpty());
    }

    @Test
    void popIfEmpty() {
        assertThrows(NoSuchElementException.class, driver::pop);
    }
}