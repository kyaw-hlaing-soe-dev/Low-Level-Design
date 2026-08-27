package org.example.designprinciple.DRY;

interface TaxCalculatorInterface {
    double calculateTax(double amount);
    String getRegion();
}

class USTaxCalculator implements TaxCalculatorInterface {
    public double calculateTax(double amount) {
        return amount * 0.10;
    }

    public String getRegion() {
        return "US";
    }
}

class EUTaxCalculator implements TaxCalculatorInterface {
    public double calculateTax(double amount) {
        return amount * 0.20;
    }

    public String getRegion() {
        return "EU";
    }
}

class UKTaxCalculator implements TaxCalculatorInterface {
    public double calculateTax(double amount) {
        return amount * 0.15;
    }

    public String getRegion() {
        return "UK";
    }
}

class OrderProcessor {
    private final TaxCalculatorInterface taxCalculator;

    public OrderProcessor(TaxCalculatorInterface taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public void processOrder(double amount) {
        double tax = taxCalculator.calculateTax(amount);
        double total = amount + tax;
        System.out.printf("%s Order - Subtotal: $%.2f, Tax: $%.2f, Total: $%.2f%n",
                taxCalculator.getRegion(), amount, tax, total);
    }
}

public class TaxCalculator{
    public static void main(String[] args) {
        OrderProcessor usProcessor = new OrderProcessor(new USTaxCalculator());
        usProcessor.processOrder(100.0);

        OrderProcessor euProcessor = new OrderProcessor(new EUTaxCalculator());
        euProcessor.processOrder(100.0);

        OrderProcessor ukProcessor = new OrderProcessor(new UKTaxCalculator());
        ukProcessor.processOrder(100.0);
    }
}