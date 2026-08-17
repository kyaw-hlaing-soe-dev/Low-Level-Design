package org.example.classrelationships.aggregation;

import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

class Catalog {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product findByName(String name) {
        for (Product p : products) {
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public int getProductCount() { return products.size(); }
}

class Cart {
    private List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        items.add(product);
    }

    public void clearCart() {
        items.clear();
    }

    public double getTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    public List<Product> getItems() { return items; }
    public int getItemCount() { return items.size(); }
}

class Customer {
    private String name;
    private Cart cart;

    public Customer(String name, Cart cart) {
        this.name = name;
        this.cart = cart;
    }

    public void checkout() {
        System.out.println(name + " checking out:");
        for (Product p : cart.getItems()) {
            System.out.println("  - " + p.getName() + ": $" + p.getPrice());
        }
        System.out.println("  Total: $" + cart.getTotal());
        cart.clearCart();
    }

    public String getName() { return name; }
    public Cart getCart() { return cart; }
}

public class ShoppingCart {
    public static void main(String[] args) {
        // Create products and add to catalog
        Product laptop = new Product("Laptop", 999.99);
        Product mouse = new Product("Mouse", 29.99);
        Product keyboard = new Product("Keyboard", 79.99);

        Catalog catalog = new Catalog();
        catalog.addProduct(laptop);
        catalog.addProduct(mouse);
        catalog.addProduct(keyboard);

        // Two customers share the same catalog products
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        Customer alice = new Customer("Alice", cart1);
        Customer bob = new Customer("Bob", cart2);

        // Both customers add the same laptop to their carts
        cart1.addItem(laptop);
        cart1.addItem(mouse);
        cart2.addItem(laptop);
        cart2.addItem(keyboard);

        System.out.println("Alice's cart: " + cart1.getItemCount() + " items, $" + cart1.getTotal());
        System.out.println("Bob's cart: " + cart2.getItemCount() + " items, $" + cart2.getTotal());

        alice.checkout();

        // After Alice checks out, products still exist
        System.out.println("Catalog still has " + catalog.getProductCount() + " products");
        System.out.println("Bob's cart still has " + cart2.getItemCount() + " items");
        System.out.println("Laptop still exists: " + laptop.getName());
    }
}