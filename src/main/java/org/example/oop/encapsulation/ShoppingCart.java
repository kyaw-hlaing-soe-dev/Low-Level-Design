package org.example.oop.encapsulation;

import java.util.HashMap;
import java.util.Map;

class ShoppingCart {
    private Map<String, Double> items = new HashMap<>();
    private boolean discountApplied = false;
    private boolean isCheckedOut = false;

    public void addItem(String name, double price) {

        // Add item to cart, but reject if already checked out
        if(isCheckedOut){
            System.out.println("Cannot Checkout");
        }
        items.put(name,price);
    }

    public boolean applyDiscount(String code) {

        if(code.equals("SAVE10") && !discountApplied && !isCheckedOut){
            discountApplied = true;
            return true;
        }
        return false;
    }

    public double getTotal() {
        double total = 0;
        for(double price : items.values()){
            total += price;
        }
        if(discountApplied){
            total *= 0.9;
        }
        return total;
    }

    public void checkout() {
        if(!isCheckedOut && !items.isEmpty()){
            isCheckedOut = true;
        }
    }
}

