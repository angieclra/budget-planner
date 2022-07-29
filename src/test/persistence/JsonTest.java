package persistence;

import model.Entry;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkEntry(String name, String category, Double money, Entry entry) {
        assertEquals(name, entry.getEntryName());
        assertEquals(category, entry.getEntryCategory());
        assertEquals(money, entry.getEntryMoneySpent());
    }
}
