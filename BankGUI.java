import javax.swing.*;
import java.awt.*;

public class BankGUI extends JFrame {

    private BankService service;
    private BankAccount currentAccount;

    public BankGUI() {
        service = new BankService();

        setTitle("Bank Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showMainMenu();
        setVisible(true);
    }

    private void showMainMenu() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton createBtn = new JButton("Create Account");
        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Exit");

        panel.add(createBtn);
        panel.add(loginBtn);
        panel.add(exitBtn);

        setContentPane(panel);
        revalidate();

        createBtn.addActionListener(e -> createAccount());
        loginBtn.addActionListener(e -> login());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void createAccount() {
        String name = JOptionPane.showInputDialog(this, "Enter Name:");
        String accStr = JOptionPane.showInputDialog(this, "Enter Account Number:");
        String pass = JOptionPane.showInputDialog(this, "Set Password:");

        try {
            int accNo = Integer.parseInt(accStr);
            service.createAccount(name, accNo, pass);
            JOptionPane.showMessageDialog(this, "Account created!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void login() {
        String accStr = JOptionPane.showInputDialog(this, "Enter Account Number:");
        String pass = JOptionPane.showInputDialog(this, "Enter Password:");

        try {
            int accNo = Integer.parseInt(accStr);
            currentAccount = service.login(accNo, pass);

            if (currentAccount != null) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                showDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void showDashboard() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton balanceBtn = new JButton("Check Balance");
        JButton transBtn = new JButton("Transactions");
        JButton logoutBtn = new JButton("Logout");

        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(balanceBtn);
        panel.add(transBtn);
        panel.add(logoutBtn);

        setContentPane(panel);
        revalidate();

        depositBtn.addActionListener(e -> deposit());
        withdrawBtn.addActionListener(e -> withdraw());
        balanceBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Balance: " + currentAccount.getBalance()));
        transBtn.addActionListener(e -> currentAccount.showTransactions());
        logoutBtn.addActionListener(e -> showMainMenu());
    }

    private void deposit() {
        String amtStr = JOptionPane.showInputDialog(this, "Enter Amount:");
        try {
            double amt = Double.parseDouble(amtStr);
            currentAccount.deposit(amt);
            service.saveAccounts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
        }
    }

    private void withdraw() {
        String amtStr = JOptionPane.showInputDialog(this, "Enter Amount:");
        try {
            double amt = Double.parseDouble(amtStr);
            currentAccount.withdraw(amt);
            service.saveAccounts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
        }
    }

    public static void main(String[] args) {
        new BankGUI();
    }
}
