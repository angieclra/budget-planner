package persistence;

import model.BudgetBookEntries;
import model.Entry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BudgetBookEntries budgetBookEntries = reader.read();
            fail("IO Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBudgetBook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBudgetBook.json");
        try {
            BudgetBookEntries budgetBookEntries = reader.read();
            assertEquals("Angelique's Budget Book", budgetBookEntries.getBudgetEntriesName());
            assertEquals(0, budgetBookEntries.getNumEntries());
            assertEquals(0, budgetBookEntries.getMoneySpentTotal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBudgetBook() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBudgetBook.json");
        try {
            BudgetBookEntries budgetBookEntries = reader.read();
            assertEquals("Angelique's Budget Book", budgetBookEntries.getBudgetEntriesName());
            List<Entry> entries = budgetBookEntries.getEntries();
            assertEquals(2, entries.size());
            assertEquals(1400.0, budgetBookEntries.getMoneySpentTotal());
            checkEntry("iphone", "fun", 1000.0, entries.get(0));
            checkEntry("nintendo switch", "gaming", 400.0, entries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
