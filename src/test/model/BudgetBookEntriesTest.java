package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetBookEntriesTest {

    private BudgetBookEntries testBudgetBook;

    @BeforeEach
    public void setUp() {
        testBudgetBook = new BudgetBookEntries("Angelique's Budget Book");
    }

    @Test
    public void testConstructor() {
        assertEquals("Angelique's Budget Book", testBudgetBook.getBudgetEntriesName());
    }

    @Test
    public void testAddToBudgetEntries() {
        testBudgetBook.addToBudgetEntries(new Entry("iphone", "fun", 1500));
        assertEquals(1500, testBudgetBook.getMoneySpentTotal());
        assertEquals(1, testBudgetBook.getNumEntries());
    }

    @Test
    public void testAddMultipleEntriesToBudgetEntries() {
        Entry entry1 = new Entry("rent", "housing", 1200);
        Entry entry2 = new Entry("noodles", "food", 15);

        testBudgetBook.addToBudgetEntries(entry1);
        assertEquals(1200, testBudgetBook.getMoneySpentTotal());
        assertEquals(1, testBudgetBook.getNumEntries());

        testBudgetBook.addToBudgetEntries(entry2);
        assertEquals(1215, testBudgetBook.getMoneySpentTotal());
        assertEquals(2, testBudgetBook.getNumEntries());
    }

    @Test
    public void testRemoveFromBudgetEntries() {
        Entry entry1 = new Entry("rent", "housing", 1200);

        testBudgetBook.removeFromBudgetEntries("rent");
        assertEquals(0, testBudgetBook.getMoneySpentTotal());
        assertEquals(0, testBudgetBook.getNumEntries());
    }

    @Test
    public void testRemoveMultipleFromBudgetEntries() {
        Entry entry1 = new Entry("rent", "housing", 1200);
        Entry entry2 = new Entry("noodles", "food", 15);

        testBudgetBook.addToBudgetEntries(entry1);
        testBudgetBook.addToBudgetEntries(entry2);
        assertEquals(1215, testBudgetBook.getMoneySpentTotal());
        assertEquals(2, testBudgetBook.getNumEntries());
        testBudgetBook.removeFromBudgetEntries("rent");
        assertEquals(15, testBudgetBook.getMoneySpentTotal());
        assertEquals(1, testBudgetBook.getNumEntries());
        testBudgetBook.removeFromBudgetEntries("noodles");
        assertEquals(0, testBudgetBook.getMoneySpentTotal());
        assertEquals(0, testBudgetBook.getNumEntries());

    }

    @Test
    public void testRemoveFromBudgetEntriesWhenQuantityZero() {
        assertEquals(0, testBudgetBook.getMoneySpentTotal());
        assertEquals(0, testBudgetBook.getNumEntries());
        testBudgetBook.removeFromBudgetEntries("rent");
        assertEquals(0, testBudgetBook.getMoneySpentTotal());
        assertEquals(0, testBudgetBook.getNumEntries());
    }

    @Test
    public void testRemoveFromBudgetEntriesWhenSpendingIsNotThere() {
        Entry entry1 = new Entry("rent", "housing", 1200);
        Entry entry2 = new Entry("noodles", "food", 15);

        testBudgetBook.addToBudgetEntries(entry1);
        testBudgetBook.addToBudgetEntries(entry2);
        assertEquals(1215, testBudgetBook.getMoneySpentTotal());
        assertEquals(2, testBudgetBook.getNumEntries());
        testBudgetBook.removeFromBudgetEntries("ramen");
        assertEquals(1215, testBudgetBook.getMoneySpentTotal());
        assertEquals(2, testBudgetBook.getNumEntries());
    }

    @Test
    public void testGetNumEntries() {
        assertEquals(0, testBudgetBook.getNumEntries());
        Entry entry1 = new Entry("rent", "housing", 1200);
        Entry entry2 = new Entry("noodles", "food", 15);
        testBudgetBook.addToBudgetEntries(entry1);
        testBudgetBook.addToBudgetEntries(entry2);
        assertEquals(2, testBudgetBook.getNumEntries());
    }

    @Test
    public void testPrintInvoice() {
        testBudgetBook.addToBudgetEntries(new Entry("rent", "housing", 1200));
        assertEquals("INVOICE\n_____________\nName: RENT\n" + "Category: HOUSING\n" +
                        "Money Spent: $1200.0\n--------------",
                testBudgetBook.printSummaryBudgetBook());
    }

}
