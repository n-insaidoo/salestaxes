package org.salestaxes.shopping;

public class ReceiptItem {
    private final int quantity;
    private final String productName;
    private final String cost;

    public ReceiptItem(int quantity, String productName, String cost) {
        this.quantity = quantity;
        this.productName = productName;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return quantity + " " + productName + ": " + cost + "\n";
    }
}