package ui;

import model.BudgetBookEntries;
import model.Entry;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BudgetBookFrame frame = new BudgetBookFrame();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
