package lsp2;

import java.util.ArrayList;
import java.util.List;

// Base abstract class or interface for common properties
abstract class BankAccount {
    protected double balance;
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
        balance += amount;
    }
    public double getBalance() { return balance; }
}

// Interface for accounts that allow withdrawals
interface Withdrawable {
    void withdraw(double amount);
}

// Standard account implements both
class CurrentAccount extends BankAccount implements Withdrawable {
    @Override
    public void withdraw(double amount) {
        if (amount > balance) throw new IllegalArgumentException("Not enough money");
        balance -= amount;
        System.out.println("Withdrew: " + amount);
    }
}

// Fixed Term only extends BankAccount (cannot withdraw)
class FixedTermAccount extends BankAccount {
    // No withdraw method here, so no LSP violation
}

public class BankingDemo {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new CurrentAccount());
        accounts.add(new FixedTermAccount());

        for (BankAccount acc : accounts) {
            acc.deposit(1000);
            System.out.println("Balance: " + acc.getBalance());
            // We do not call withdraw() here blindly on BankAccount
        }
        
        // Specific logic for withdrawable accounts
        CurrentAccount myAcc = new CurrentAccount();
        myAcc.deposit(500);
        myAcc.withdraw(100); 
    }
}