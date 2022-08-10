package ui;

import model.Entry;
import model.BudgetBookEntries;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.*;

// Budget Book GUI Frame Application
public class BudgetBookFrame extends JFrame {
    private double totalBudgetSpending;
    private int numberItems;

    private BudgetBookEntries budgetBookEntries;

    private EventLog eventLog;

    private static JFrame frame;

    private JTextField totalSpending;
    private JTextField totalItems;
    private JTextField nameEntry;
    private JTextField categoryEntry;
    private JTextField moneySpentEntry;
    private JTextField removeEntry;

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel namePanel;
    private JPanel categoryPanel;
    private JPanel moneyPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel buttonsPanel;
    private JPanel buttonRemovePanel;
    private JPanel panelForRemove;

    private JLabel label;
    private JLabel label1;
    private JLabel name;
    private JLabel category;
    private JLabel moneySpent;

    private JTextArea invoicePanel;

    private JButton addSpendingButton;
    private JButton removeSpendingButton;
    private JButton viewInvoiceButton;
    private JButton finishShoppingButton;
    private JButton quitShoppingButton;
    private JButton saveButton;
    private JButton loadButton;

    private JScrollPane scrollPane;

    // MODIFIES: this
    // EFFECTS: sets the frame of the application
    public BudgetBookFrame() throws IOException {
        budgetBookEntries = new BudgetBookEntries("Angelique's Budget Book ♡");
        initializeTopPanel(budgetBookEntries);
        initializeBottomPanel();
        initializeInvoicePanel();
        initializeFinishBudgeting();
        initializeQuitBudgeting();
    }

    // EFFECTS: set the text fields for the total spending and total quantity of the budget book
    private void setTotalField() {
        totalSpending = new JTextField("$0.00", 5);
        totalSpending.setEditable(false);
        totalSpending.setEnabled(false);
        totalSpending.setDisabledTextColor(Color.BLACK);

        totalItems = new JTextField("0", 2);
        totalItems.setEditable(false);
        totalItems.setEnabled(false);
        totalItems.setDisabledTextColor(Color.BLACK);
    }

    // MODIFIES: this
    // EFFECTS: set the title of the frame
    private void titleFrame(BudgetBookEntries budgetBookEntries) {
        setTitle(budgetBookEntries.getBudgetEntriesName());
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                quit();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initialize top panel of the frame, with the labels for the total spending
    // of the budget book and total quantity of the items of the budget book, along with the text fields
    // for displaying the total spending and total quantity
    public void initializeTopPanel(BudgetBookEntries budgetBookEntries) throws IOException {
        titleFrame(budgetBookEntries);
        setTotalField();

        topPanel = new JPanel();
        topPanel.setBackground(new Color(255, 136, 134));
        topPanel.setBorder(new TitledBorder("Summary "));
        TitledBorder titledBorder = (TitledBorder) topPanel.getBorder();
        titledBorder.setTitleFont(new Font("Monospaced", Font.ITALIC, 15));
        titledBorder.setTitleColor(Color.WHITE);

        addLabels();

        addTextFieldsToTopPanel();
        add(topPanel, BorderLayout.NORTH);

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        topPanel.setBackground(new Color(255, 204, 203));
        topPanel.setBorder(new TitledBorder(new EtchedBorder(), "Enter your spending:"));
        TitledBorder titledBorder1 = (TitledBorder) topPanel.getBorder();
        titledBorder1.setTitleFont(new Font("Monospaced", Font.ITALIC, 15));

        labelsForEntry();

        add(topPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        pack();
    }

    // EFFECTS: add all the labels and text fields to top panel
    private void addTextFieldsToTopPanel() {
        topPanel.add(label);
        topPanel.add(totalSpending);
        topPanel.add(label1);
        topPanel.add(totalItems);
    }

    // EFFECTS: labels and fields for user's entry
    private void labelsForEntry() {
        setJLabelsForEntry();
        setJTextFieldsAndPanelsForEntry();

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(255, 204, 203));

        buttonRemovePanel = new JPanel();
        buttonRemovePanel.setBackground(new Color(255, 204, 203));

        panelRemoveMade();

        addButton();
        removeButton();

        topPanel.add(buttonsPanel);
        topPanel.add(panelForRemove);
        topPanel.add(buttonRemovePanel);
    }

    private void setJTextFieldsAndPanelsForEntry() {
        nameEntry = new JTextField(10);
        namePanel = new JPanel();
        namePanel.add(name);
        namePanel.add(nameEntry);
        namePanel.setBackground(new Color(255, 204, 203));

        topPanel.add(namePanel);

        categoryEntry = new JTextField(10);
        categoryPanel = new JPanel();
        categoryPanel.add(category);
        categoryPanel.add(categoryEntry);
        topPanel.add(categoryPanel);
        categoryPanel.setBackground(new Color(255, 204, 203));

        moneySpentEntry = new JTextField(10);
        moneyPanel = new JPanel();
        moneyPanel.add(moneySpent);
        moneyPanel.add(moneySpentEntry);
        moneyPanel.setBackground(new Color(255, 204, 203));
        topPanel.add(moneyPanel);
    }

    private void panelRemoveMade() {
        panelForRemove = new JPanel();
        panelForRemove.setBorder(new TitledBorder(new EtchedBorder(), "Want to remove a spending?"));
        TitledBorder titledBorder2 = (TitledBorder) panelForRemove.getBorder();
        titledBorder2.setTitleFont(new Font("Monospaced", Font.ITALIC, 15));
        JLabel removeItem = new JLabel("Name of the spending you would like to remove: ");
        removeItem.setFont(new Font("Helvetica", Font.BOLD, 20));
        removeItem.setForeground(Color.WHITE);
        panelForRemove.add(removeItem);
        removeEntry = new JTextField(10);
        panelForRemove.add(removeEntry);
        panelForRemove.setBackground(new Color(255, 204, 203));
    }

    private void setJLabelsForEntry() {
        name = new JLabel("Name: ");
        name.setFont(new Font("Helvetica", Font.BOLD, 20));
        name.setForeground(Color.WHITE);

        category = new JLabel("Category: ");
        category.setFont(new Font("Helvetica", Font.BOLD, 20));
        category.setForeground(Color.WHITE);

        moneySpent = new JLabel("Money Spent: $");
        moneySpent.setFont(new Font("Helvetica", Font.BOLD, 20));
        moneySpent.setForeground(Color.WHITE);
    }

    // EFFECTS: remove button is made. remove entry from list if valid
    private void removeButton() {
        removeSpendingButton = new JButton("Remove Spending");
        removeSpendingButton.setBounds(100,100,100,4);
        removeSpendingButton.setForeground(Color.BLACK);
        removeSpendingButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // MODIFIES: this
        // EFFECTS: remove item to shopping cart when button clicked
        removeSpendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeSpending = removeEntry.getText();
                budgetBookEntries.removeFromBudgetEntries(removeSpending);
                updateTotalPrice();
                updateTotalQuantity();
//                items.removeItemEvent();
            }
        });
        buttonRemovePanel.add(removeSpendingButton);
    }

    // EFFECTS: add button is made. add entry to list
    private void addButton() {
        addSpendingButton = new JButton("Add Spending");
        addSpendingButton.setBounds(100,100,100,4);
        addSpendingButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // MODIFIES: this
        // EFFECTS: add entry to budget book when button clicked
        addSpendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameEntry.getText();
                String category = categoryEntry.getText();
                Double money = Double.parseDouble(moneySpentEntry.getText());
                Entry entry = new Entry(name, category, money);

                budgetBookEntries.addToBudgetEntries(entry);
                updateTotalPrice();
                updateTotalQuantity();
            }
        });
        buttonsPanel.add(addSpendingButton);
    }

    // EFFECTS: add labels for total price and quantity
    private void addLabels() {
        label = new JLabel("Total Spending:");
        label.setFont(new Font("Helvetica", Font.BOLD, 25));
        label.setForeground(Color.WHITE);

        label1 = new JLabel("Total Quantity:");
        label1.setFont(new Font("Helvetica", Font.BOLD, 25));
        label1.setForeground(Color.WHITE);
    }

    // EFFECTS: updates the text field of totalPrice everytime a user
    // adds or remove an item off the shopping cart
    public void updateTotalPrice() {
        totalBudgetSpending = budgetBookEntries.getMoneySpentTotal();
        NumberFormat formatter = new DecimalFormat("#0.00");
        formatter.format(totalBudgetSpending);
        totalSpending.setText(NumberFormat.getCurrencyInstance().format(totalBudgetSpending));
    }

    // EFFECTS: updates the text field of totalItems everytime a user
    // adds or remove an item off the shopping cart
    public void updateTotalQuantity() {
        numberItems = budgetBookEntries.getNumEntries();
        totalItems.setText(NumberFormat.getIntegerInstance().format(numberItems));
    }


    // EFFECTS: initialize bottom panel along with add and remove buttons on the panel
    public void initializeBottomPanel() {
        saveAndLoadButton();
        writeAndReadData();

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255, 168, 181));
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // EFFECTS: save and load buttons are made
    private void saveAndLoadButton() {
        // save button
        saveButton = new JButton("Save Budget Book");
        saveButton.setBounds(100, 100, 100, 4);
        saveButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // load button
        loadButton = new JButton("Load Budget Book");
        loadButton.setBounds(100, 100, 100, 4);
        loadButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    // EFFECTS: display list of items that the user bought on the right side of the panel
    public void initializeInvoicePanel() {
        rightPanel = new JPanel();
        rightPanel.setBorder(new TitledBorder(new EtchedBorder(), "Invoice "));
        rightPanel.setBackground(new Color(255, 233, 224));
        TitledBorder titledBorder = (TitledBorder) rightPanel.getBorder();
        titledBorder.setTitleFont(new Font("Monospaced", Font.ITALIC, 15));

        initializeInvoicePane();
        viewInvoice();

        add(rightPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: initialize invoice pane with scroll pane, user can scroll if items in large quantity
    private void initializeInvoicePane() {
        invoicePanel = new JTextArea(30, 20);
        invoicePanel.setEditable(false);
        invoicePanel.setEnabled(false);
        invoicePanel.setDisabledTextColor(Color.BLACK);
        invoicePanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(invoicePanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.add(scrollPane);
    }

    // EFFECTS: view invoice for budget book, updates everytime a user adds or remove a new spending/ entry
    private void viewInvoice() {
        viewInvoiceButton = new JButton("View Budget Book Spending");
        viewInvoiceButton.setBounds(100, 100, 100, 4);
        viewInvoiceButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bottomPanel.add(viewInvoiceButton);

        // print invoice with updated number of items and total price
        viewInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String invoiceItems = "\t" + budgetBookEntries.printSummaryBudgetBook();
                invoiceItems += "\n Total Items Spent: " + totalItems.getText()
                        + "\n Total Spending: " + totalSpending.getText() + "\n"
                        + "\n Bye for now! See ya soon :)";
                invoicePanel.setText(invoiceItems);
//                items.printInvoiceEvent();
            }
        });
        pack();
    }

    // EFFECTS: button for user to finish shopping,
    // shows image of thank you message when button is clicked
    public void initializeFinishBudgeting() {
        finishShoppingButton = new JButton("Finish Budgeting");
        finishShoppingButton.setBounds(100, 100, 100, 4);
        finishShoppingButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bottomPanel.add(finishShoppingButton);

        // MODIFIES: this
        // EFFECTS: show new frame with thank you image when button clicked.
        // when thank you image is closed event log will be printed in console
        finishShoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNewFrame();

                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        quit();
                    }
                });
            }
        });
        pack();
    }

    // EFFECTS: sets new frame with thank you image
    private void setNewFrame() {
        frame = new JFrame("Bye for now! We'll see ya soon  ︎◡̈");
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new FlowLayout());

        ImageIcon icon = new ImageIcon("./data/thank you.gif");
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.pack();
        frame.setSize(500, 500);
        dispose();
        frame.setVisible(true);
    }

    // EFFECTS: button for user to quit budgeting,
    // prints all logs to console when quitting application
    public void initializeQuitBudgeting() {
        quitShoppingButton = new JButton("Quit Budgeting");
        quitShoppingButton.setBounds(100, 100, 100, 4);
        quitShoppingButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bottomPanel.add(quitShoppingButton);

        // MODIFIES: this
        // EFFECTS: show new frame with thank you image when button clicked
        quitShoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });
    }

    // EFFECTS: load and write the data to JSON file
    public void writeAndReadData() {
        loadData();
        saveData();
    }

    // EFFECTS: when button is pressed it saves current state of budget book,
    // along with the information in it to JSON data
    private void saveData() {
        // MODIFIES: this
        // EFFECTS: save to JSON file when button clicked
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonWriter writer = new JsonWriter("./data/budgetBook.json");
                try {
                    writer.open();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                writer.write(budgetBookEntries);
                writer.close();
            }
        });
    }

    // EFFECTS: load JSON data, updates the total spending and total quantity of the budget
    // book when button is pressed. user is allowed to retrieve data from existing JSON file
    private void loadData() {
        // MODIFIES: this
        // EFFECTS: load current JSON file when button clicked
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonReader reader = new JsonReader("./data/budgetBook.json");
                try {
                    budgetBookEntries = reader.read();
                    updateTotalQuantity();
                    updateTotalPrice();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    // EFFECTS: prints event that is logged in the event log, including adding items,
    // removing items, and printing invoice
    private void quit() {
        eventLog = EventLog.getInstance();

        for (Event event : eventLog) {
            System.out.println(event + "\n");
        }

        System.exit(0);
    }

}