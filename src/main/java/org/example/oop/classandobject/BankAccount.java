package org.example.oop.classandobject;

public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if(amount >= 0){
            System.out.println("Can't Deposit");
        }
        balance +=amount;
    }

    public boolean withdraw(double amount) {
        if(amount > 0 && amount <= balance){
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

