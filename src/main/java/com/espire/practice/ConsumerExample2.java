package com.espire.practice;

import java.util.function.Consumer;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void displayProduct() {
        System.out.println(name + ": $" + price);
    }
}

public class ConsumerExample2 {
    public static void main(String[] args) {
        Product product = new Product("Laptop", 1200.0);

        // Consumer to apply discount
        Consumer<Product> applyDiscount = p -> p.setPrice(p.getPrice() * 0.9);

        // Applying discount
        applyDiscount.accept(product);
        
        // Display updated product details
        product.displayProduct();  // Laptop: $1080.0
    }
}
