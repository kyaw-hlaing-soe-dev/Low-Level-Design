package org.example.oop.polymorphism;

abstract class Discount {
    protected String label;

    public Discount(String label) {
        this.label = label;
    }

    abstract double apply(double price);

    public void describe(double originalPrice) {
        double discountedPrice = apply(originalPrice);
        System.out.println(label + ": $" + String.format("%.2f", originalPrice)
                + " -> $" + String.format("%.2f", discountedPrice));
    }
}

class PercentageDiscount extends Discount {
    private double percentage;

    public PercentageDiscount(double percentage) {
        super(percentage + "% off");
        this.percentage = percentage;
    }

    double apply(double price) {
        return price * (1 - percentage / 100);
    }
}

class FlatDiscount extends Discount {
    private double amount;

    public FlatDiscount(double amount) {
        super("$" + amount + " off");
        this.amount = amount;
    }

    double apply(double price) {
        return Math.max(price - amount, 0);
    }
}

class BuyOneGetOneFree extends Discount {
    public BuyOneGetOneFree() {
        super("Buy 1 Get 1 Free");
    }

    double apply(double price) {
        return price / 2;
    }
}

class OrderProcessor {
    public void processOrder(String itemName, double price, Discount discount) {
        System.out.println("Item: " + itemName);
        discount.describe(price);
    }
}

