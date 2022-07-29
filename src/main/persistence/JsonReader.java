package persistence;

import model.BudgetBookEntries;
import model.Entry;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads shopping cart from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BudgetBookEntries read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBudgetBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private java.lang.String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<java.lang.String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses budget book from JSON object and returns it
    private BudgetBookEntries parseBudgetBook(JSONObject jsonObject) {
        String name = jsonObject.getString("budgetBookName");
        BudgetBookEntries budgetBookEntries = new BudgetBookEntries(name);
        addEntries(budgetBookEntries, jsonObject);
        return budgetBookEntries;
    }

    // MODIFIES: budgetBookEntries
    // EFFECTS: parses items from JSON object and adds them to budget book entries
    private void addEntries(BudgetBookEntries budgetBookEntries, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("spending");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(budgetBookEntries, nextEntry);
        }
    }

    // MODIFIES: budgetBookEntries
    // EFFECTS: parses item from JSON object and adds it to budget book
    private void addEntry(BudgetBookEntries budgetBookEntries, JSONObject jsonObject) {
        String name = jsonObject.getString("entryName");
        String category = jsonObject.getString("entryCategory");
        Double money = jsonObject.getDouble("entryMoneySpent");
        Entry entry = new Entry(name, category, money);
        budgetBookEntries.addToBudgetEntries(entry);
    }
}
