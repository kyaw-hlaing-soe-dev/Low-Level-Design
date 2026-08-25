package org.example.classrelationships.dependency;

class InventoryChecker {
    public boolean checkStock(String itemName, int quantity) {
        System.out.println("Checking stock for " + itemName + " (x" + quantity + ")");
        System.out.println("Stock available: true");
        return true;
    }
}

class PriceCalculator {
    public double calculate(String itemName, int quantity) {
        double unitPrice = 1249.99;
        double total = unitPrice * quantity;
        System.out.printf("Calculating price: %s x %d = $%.2f%n", itemName, quantity, total);
        return total;
    }
}

class InvoiceGenerator {
    public String generate(String itemName, int quantity, double total) {
        System.out.println("Generating invoice...");
        return String.format("--- INVOICE ---\nItem: %s\nQuantity: %d\nTotal: $%.2f\n--- END ---",
                itemName, quantity, total);
    }
}

class OrderProcessor {
    public String processOrder(String itemName, int quantity,
                               InventoryChecker checker, PriceCalculator calculator,
                               InvoiceGenerator generator) {
        boolean inStock = checker.checkStock(itemName, quantity);
        if (!inStock) {
            return "Order rejected: " + itemName + " is out of stock.";
        }
        double total = calculator.calculate(itemName, quantity);
        return generator.generate(itemName, quantity, total);
    }
}

public class OrderProcess {
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();

        InventoryChecker checker = new InventoryChecker();
        PriceCalculator calculator = new PriceCalculator();
        InvoiceGenerator generator = new InvoiceGenerator();

        String invoice = processor.processOrder("Laptop", 2,
                checker, calculator, generator);
        System.out.println(invoice);
    }
}