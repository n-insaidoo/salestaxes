package org.salestaxes.shopping;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.salestaxes.shopping.Product.Tax;

public class CheckoutTest {

    private Checkout checkout;

    @Before
    public void setup() {
        this.checkout = new Checkout();
    }

    @Test
    public void shouldCheckoutWithEmptyCartProduceEmptyReceipt() {
        Cart cart = new Cart(Collections.emptyList());

        Receipt receipt = checkout.checkout(cart);

        assertEquals(""
                + "Sale taxes: 0.00\n"
                + "Total: 0.00\n",
                receipt.toString());
    }

    @Test
    public void shouldCheckOutProduceReceipt() {
        Cart cart = new Cart(asList(
        new CartItem(2, new Product("book", 12.49, Collections.emptyList())),
        new CartItem(1, new Product("music CD", 14.99, asList(Tax.NON_ESSENTIALS))),
        new CartItem(1, new Product("imported bottle of perfume", 27.99, asList(Tax.NON_ESSENTIALS, Tax.IMPORTED)))
        ));

        Receipt receipt = checkout.checkout(cart);

        assertEquals(""
                + "2 book: 25.00\n"
                + "1 music CD: 16.50\n"
                + "1 imported bottle of perfume: 32.20\n"
                + "Sale taxes: 5.70\n"
                + "Total: 73.65\n",
                receipt.toString());
    }
}
