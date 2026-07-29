package org.example.oop.classandobject;

public class BankAccountApp {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456", "John Doe");
        account.deposit(1000);
        System.out.println(account.getBalance());

        boolean success = account.withdraw(500);
        System.out.println(success);
        System.out.println(account.getBalance());

        success = account.withdraw(1000);
        System.out.println(success);
        System.out.println(account.getBalance());
    }
}
