package org.example.oop.classandobject;

public class OnlineFoodApp {
    public static void main(String[] args) {
        OnlineFoodOrder order1 = new OnlineFoodOrder("ORD-101", "Alice");
        order1.addItem("Pizza", 12.99);
        order1.addItem("Garlic Bread", 4.99);
        order1.addItem("Coke", 2.49);
        order1.placeOrder();

        OnlineFoodOrder order2 = new OnlineFoodOrder("ORD-102", "Bob");
        order2.addItem("Burger", 9.99);
        order2.addItem("Fries", 3.99);
        // Bob hasn't placed his order yet

        order1.displayOrder();
        System.out.println();
        order2.displayOrder();
    }
}