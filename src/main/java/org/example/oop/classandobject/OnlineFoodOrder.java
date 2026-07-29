package org.example.oop.classandobject;

import java.util.ArrayList;
import java.util.List;

public class OnlineFoodOrder {
    private String orderId;
    private String customerName;
    private List<String> items;
    private double totalAmount;
    private boolean isPlaced;

    public OnlineFoodOrder(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        this.isPlaced = false;
    }

    // Only allows adding items before the order is placed
    public void addItem(String name, double price) {
        if (isPlaced) {
            System.out.println("Cannot modify a placed order.");
            return;
        }
        items.add(name);
        totalAmount += price;
    }

    // Places the order if it has at least one item and hasn't been placed yet
    public boolean placeOrder() {
        if (isPlaced || items.isEmpty()) {
            return false;
        }
        isPlaced = true;
        return true;
    }

    public int getItemCount() {
        return items.size();
    }

    public void displayOrder() {
        String status = isPlaced ? "PLACED" : "PENDING";
        System.out.println("Order " + orderId + " (" + customerName + ") - " + status);
        for (String item : items) {
            System.out.println("  - " + item);
        }
        System.out.printf("  Total: $%.2f%n", totalAmount);
    }
}


