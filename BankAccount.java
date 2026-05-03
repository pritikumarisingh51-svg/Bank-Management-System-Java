import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BankAccount {
    private String name;
    private int accountNumber;
    private String password;
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(String name, int accountNumber, String password, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public String getData() {
        return name + "," + accountNumber + "," + password + "," + balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount);
            JOptionPane.showMessageDialog(null, "Deposit successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            JOptionPane.showMessageDialog(null, "Insufficient balance!");
        } else if (amount <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid amount!");
        } else {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
            JOptionPane.showMessageDialog(null, "Withdrawal successful!");
        }
    }

    public void showTransactions() {
        String msg = "Transaction History:\n";
        for (String t : transactions) {
            msg += t + "\n";
        }
        JOptionPane.showMessageDialog(null, msg);
    }
}