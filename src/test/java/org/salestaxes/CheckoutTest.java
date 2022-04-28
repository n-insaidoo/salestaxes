package org.salestaxes;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;
import org.salestaxes.Product.Tax;

public class CheckoutTest {

    @Test
    public void shouldCheckoutWithEmptyCartProduceEmptyReceipt() {
        Cart cart = new Cart(Collections.emptyList());

        Checkout checkout = new Checkout(cart);

        assertEquals(""
                + "Sale taxes: 0.00\n"
                + "Total: 0.00\n",
                checkout.checkout().toString());
    }

    @Test
    public void shouldCheckOutProduceReceipt() {
        Cart cart = new Cart(asList(
        new CartItem(2, new Product("book", 12.49, Collections.emptyList())),
        new CartItem(1, new Product("music CD", 14.99, asList(Tax.NON_ESSENTIALS))),
        new CartItem(1, new Product("imported bottle of perfume", 27.99, asList(Tax.NON_ESSENTIALS, Tax.IMPORTED)))
        ));

        Checkout checkout = new Checkout(cart);

        assertEquals(""
                + "2 book: 24.98\n"
                + "1 music CD: 16.49\n"
                + "1 imported bottle of perfume: 32.19\n"
                + "Sale taxes: 5.70\n"
                + "Total: 73.66\n",
                checkout.checkout().toString());
    }
}
