package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

// Represents a budget book with the user's entries in it
public class BudgetBookEntries {
    // array list consisting of entries of spending
    private ArrayList<Entry> entries;
    // changing properties of a budget book, name of budget book assigned to field entriesName,
    // total money spent by user is assigned to field totalMoney
    private String entriesName;
    private double totalMoney;

    // EFFECTS: constructs budget book
    public BudgetBookEntries(String entriesName) {
        this.entriesName = entriesName;
        entries = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add entry to budget book with the given name, category, and money spent
    public void addToBudgetEntries(Entry entry) {
        entries.add(entry);
        totalMoney += entry.getEntryMoneySpent();
    }

    // MODIFIES: this
    // EFFECTS: remove entry from budget book with the given name
    public void removeFromBudgetEntries(String name) {
        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            if (entry.getEntryName().equals(name)) {
                entries.remove(entry);
                totalMoney =  getMoneySpentTotal() - entry.getEntryMoneySpent();
                return;
            }
        }
    }

    // EFFECTS: return the total number of entries in the user's budget book
    public int getNumEntries() {
        return entries.size();
    }

    // EFFECTS: return the name of the user's budget book
    public String getBudgetEntriesName() {
        return entriesName;
    }

    // EFFECTS: return the total of the money spent in spendings from the whole budget book
    public double getMoneySpentTotal() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        formatter.format(totalMoney);
        return totalMoney;
    }

    // MODIFIES: this
    // EFFECTS: prints the invoice for the user along with the name of spending, alongside with the
    // category and money spent, and total of the whole budget book made by the user
    public String printInvoice() {
        String content;

        content = "INVOICE\n" + "_____________";
        for (int i = 0; i < getNumEntries(); i++) {
            content += "\nName: " + entries.get(i).getEntryName().toUpperCase(Locale.ROOT) + "\nCategory: "
                    + entries.get(i).getEntryCategory().toUpperCase(Locale.ROOT) + "\nMoney Spent: $"
                    + entries.get(i).getEntryMoneySpent() + "\n--------------";
        }
        return content;
    }
}
