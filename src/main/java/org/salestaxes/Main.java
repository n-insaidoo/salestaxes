package org.salestaxes;

import static org.salestaxes.shopping.Product.Tax.ESSENTIALS;
import static org.salestaxes.shopping.Product.Tax.NON_ESSENTIALS;

import java.util.HashMap;
import java.util.Map;

import org.salestaxes.parser.CartParser;
import org.salestaxes.shopping.Cart;
import org.salestaxes.shopping.Checkout;
import org.salestaxes.shopping.Receipt;
import org.salestaxes.shopping.Product.Tax;

public class Main {

    private static final Map<String, Tax> inventory;
    static {
        inventory = new HashMap<>();
        inventory.put("book", ESSENTIALS);
        inventory.put("music CD", NON_ESSENTIALS);
        inventory.put("chocolate bar", ESSENTIALS);
        inventory.put("imported box of chocolates", ESSENTIALS);
        inventory.put("imported bottle of perfume", NON_ESSENTIALS);
        inventory.put("bottle of perfume", NON_ESSENTIALS);
        inventory.put("packet of headache pills", ESSENTIALS);
        inventory.put("box of imported chocolates", ESSENTIALS);
    }

    public static void main(String[] args) {
        Cart cart = new CartParser(inventory).parse(args);
        Checkout checkout = new Checkout();
        Receipt receipt = checkout.checkout(cart);
        System.out.println(receipt);
    }
}
