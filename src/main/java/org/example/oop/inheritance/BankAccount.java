package org.example.oop.inheritance;

class BankAccount {
    protected String ownerName;
    protected String accountNumber;
    protected double balance;

    public BankAccount(String ownerName, String accountNumber, double balance) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void displayAccount() {
        System.out.println(ownerName + " (" + accountNumber + ") | Balance: "
                + String.format("$%.2f", balance));
    }
}

class SavingsAccount extends BankAccount {
    private final double interestRate;

    public SavingsAccount(String ownerName, String accountNumber,
                          double balance, double interestRate) {
        super(ownerName, accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= 100) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void applyInterest() {
        balance += balance * interestRate / 100;
    }
}

class CheckingAccount extends BankAccount {
    private final double overdraftLimit;

    public CheckingAccount(String ownerName, String accountNumber,
                           double balance, double overdraftLimit) {
        super(ownerName, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

