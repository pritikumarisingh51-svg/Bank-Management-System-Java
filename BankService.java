import java.util.*;
import java.io.*;

public class BankService {

    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private final String FILE_NAME = "accounts.txt";

    public BankService() {
        loadAccounts();
    }

    public void createAccount(String name, int accNo, String password) {
        if (findAccount(accNo) != null) {
            System.out.println("Account already exists!");
            return;
        }

        BankAccount acc = new BankAccount(name, accNo, password, 0);
        accounts.add(acc);
        saveAccounts();

        System.out.println("Account created successfully!");
    }

    public BankAccount login(int accNo, String password) {
        BankAccount acc = findAccount(accNo);
        if (acc != null && acc.getPassword().equals(password)) {
            return acc;
        }
        return null;
    }

    public BankAccount findAccount(int accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    public void saveAccounts() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));
            for (BankAccount acc : accounts) {
                writer.println(acc.getData());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving data!");
        }
    }

    public void loadAccounts() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                String name = data[0];
                int accNo = Integer.parseInt(data[1]);
                String pass = data[2];
                double bal = Double.parseDouble(data[3]);

                accounts.add(new BankAccount(name, accNo, pass, bal));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading data!");
        }
    }
}
