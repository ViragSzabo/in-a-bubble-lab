package phase1_teams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueVaultTest {
    UniqueVault uniqueVault;

    @BeforeEach
    void setUp() {
        uniqueVault = new UniqueVault();
    }

    @Test
    void add() {
        assertTrue(uniqueVault.isEmpty());

        assertTrue(uniqueVault.add(5));
        assertFalse(uniqueVault.isEmpty());
        assertFalse(uniqueVault.add(5));
        assertTrue(uniqueVault.contains(5));
        assertEquals(1, uniqueVault.size());
    }

    @Test
    void remove() {
        uniqueVault.add("testing set");
        assertTrue(uniqueVault.contains("testing set"));
        assertTrue(uniqueVault.remove("testing set"));
        assertFalse(uniqueVault.contains("testing set"));
    }
}