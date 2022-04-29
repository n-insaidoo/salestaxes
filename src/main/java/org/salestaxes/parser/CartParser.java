package org.salestaxes.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.salestaxes.shopping.Cart;
import org.salestaxes.shopping.CartItem;
import org.salestaxes.shopping.Product;
import org.salestaxes.shopping.Product.Tax;

public class CartParser {

    private Map<String, Tax> inventory;

    public CartParser(Map<String, Tax> inventory) {
        this.inventory = inventory;
    }

    public Cart parse(String... cartItems) {
        List<CartItem> items = new ArrayList<>();

        for (String cartItemLine : cartItems) {
            if (cartItemLine.isEmpty())
                continue;

            int quantity = parseQuantity(cartItemLine);
            double price = parsePrice(cartItemLine);
            String productName = parseProductName(cartItemLine);

            Product product = new Product(productName, price, assignTax(productName));
            items.add(new CartItem(quantity, product));
        }

        return new Cart(items);
    }

    private List<Tax> assignTax(String productName) {
        List<Tax> taxes = new ArrayList<>();

        if (productName.toLowerCase().contains("imported")) {
            taxes.add(Tax.IMPORTED);
        }

        taxes.add(inventory.get(productName));
        return taxes;
    }

    private String parseProductName(String cartItemLine) {
        int priceDigitIndex = cartItemLine.lastIndexOf("at");
        String name = cartItemLine.substring(1, priceDigitIndex).trim();
        return name;
    }

    private double parsePrice(String cartItemLine) {
        int priceDigitIndex = cartItemLine.lastIndexOf("at") + 2;
        String stringPrice = cartItemLine.substring(priceDigitIndex).trim();
        return Double.parseDouble(stringPrice);
    }

    private int parseQuantity(String cartItemLine) {
        return Integer.parseInt(cartItemLine.substring(0,1));
    }

}
