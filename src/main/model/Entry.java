package model;

// Represents a single entry with a name, category, and amount of money spent
public class Entry {
    // name of the spending
    private String entryName;
    // category of the spending
    private String entryCategory;
    // amount of money spent of the spending
    private double entryMoney;

    // EFFECTS: name of entry is set to entryName,
    // category of entry is set to entryCategory,
    // money spent to the entry is set to entryMoney
    public Entry(String entryName, String entryCategory, double entryMoney) {
        this.entryName = entryName;
        this.entryCategory = entryCategory;
        this.entryMoney = entryMoney;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public void setEntryCategory(String entryCategory) {
        this.entryCategory = entryCategory;
    }

    public void setEntryMoneySpent(double entryMoney) {
        this.entryMoney = entryMoney;
    }

    public String getEntryName() {
        return entryName;
    }

    public String getEntryCategory() {
        return entryCategory;
    }

    public double getEntryMoneySpent() {
        return entryMoney;
    }




}
