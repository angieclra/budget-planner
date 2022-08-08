package ui;

import model.BudgetBookEntries;
import model.Entry;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BudgetBookEntries list = new BudgetBookEntries("Angelique's Budget Book ♡");

        BudgetBookFrame frame = new BudgetBookFrame();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
