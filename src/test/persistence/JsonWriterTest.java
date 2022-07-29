package persistence;

import model.Entry;
import model.BudgetBookEntries;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            BudgetBookEntries budgetBookEntries = new BudgetBookEntries("Angelique's Budget Book");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBudgetBook() {
        try {
            BudgetBookEntries budgetBookEntries = new BudgetBookEntries("Angelique's Budget Book");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingCart.json");
            writer.open();
            writer.write(budgetBookEntries);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingCart.json");
            budgetBookEntries = reader.read();
            assertEquals("Angelique's Budget Book", budgetBookEntries.getBudgetEntriesName());
            assertEquals(0, budgetBookEntries.getNumEntries());
            assertEquals(0, budgetBookEntries.getMoneySpentTotal());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBudgetBook() {
        try {
            BudgetBookEntries budgetBookEntries = new BudgetBookEntries("Angelique's Budget Book");
            budgetBookEntries.addToBudgetEntries(new Entry("iphone", "fun", 1000.0));
            budgetBookEntries.addToBudgetEntries(new Entry("nintendo switch", "gaming", 400.0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingCart.json");
            writer.open();
            writer.write(budgetBookEntries);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingCart.json");
            budgetBookEntries = reader.read();
            assertEquals("Angelique's Budget Book", budgetBookEntries.getBudgetEntriesName());
            List<Entry> entries = budgetBookEntries.getEntries();
            assertEquals(2, entries.size());
            assertEquals(1400.0, budgetBookEntries.getMoneySpentTotal());
            checkEntry("iphone", "fun", 1000.0, entries.get(0));
            checkEntry("nintendo switch", "gaming", 400.0, entries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
