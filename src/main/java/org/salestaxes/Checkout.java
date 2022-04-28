package org.salestaxes;

import java.util.ArrayList;
import java.util.List;

public class Checkout {

    private final Cart cart;

    public Checkout(Cart cart) {
        this.cart = cart;
    }

    public Receipt checkout() {
        List<ReceiptItem> items = new ArrayList<>();

        double totalCost = 0;
        double saleTaxes = 0;

        for (CartItem cartItem : cart.getItems()) {
            int quantity = cartItem.getQuantity();
            Product product = cartItem.getProduct();
            double cost = calculateCost(quantity, product);
            items.add(new ReceiptItem(quantity, product.getName(), round(cost)));

            totalCost += cost;
            saleTaxes += product.taxes();
        }

        return new Receipt(items, round(totalCost), round(saleTaxes));
    }

    private String round(double cost) {
        return String.format("%.2f", cost);
    }

    private double calculateCost(int quantity, Product product) {
        double price = product.getPrice();
        double taxRate = product.getTaxRate();
        return quantity * (price + price * taxRate);
    }
}
