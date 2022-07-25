package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {

    private Entry testEntry;

    @BeforeEach
    public void setUp() {
        testEntry = new Entry("rent", "housing", 1200);
    }

    @Test
    public void testConstructor() {
        assertEquals("rent", testEntry.getEntryName());
        assertEquals("housing", testEntry.getEntryCategory());
        assertEquals(1200, testEntry.getEntryMoneySpent());
    }

    @Test
    public void testSetEntryName() {
        testEntry.setEntryName("iphone");
        assertEquals("iphone", testEntry.getEntryName());
    }

    @Test
    public void testSetEntryCategory() {
        testEntry.setEntryCategory("fun");
        assertEquals("fun", testEntry.getEntryCategory());
    }

    @Test
    public void testSetEntryMoneySpent() {
        testEntry.setEntryMoneySpent(1500);
        assertEquals(1500, testEntry.getEntryMoneySpent());
    }
}