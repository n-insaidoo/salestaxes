package org.salestaxes.shopping;

import java.util.ArrayList;
import java.util.List;

public class Checkout {

    public Receipt checkout(Cart cart) {
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
        double value = Math.round(cost * 20);
        value = value / 20;
        return String.format("%.2f", value);
    }

    private double calculateCost(int quantity, Product product) {
        double price = product.getPrice();
        double taxRate = product.getTaxRate();
        return quantity * (price + price * taxRate);
    }
}
