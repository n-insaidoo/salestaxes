package org.salestaxes.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.salestaxes.shopping.Cart;
import org.salestaxes.shopping.CartItem;
import org.salestaxes.shopping.Product;
import org.salestaxes.shopping.Product.Tax;

public class CartParserTest {

    private CartParser parser;

    @Before
    public void init() {
        Map<String, Tax> inventory = new HashMap<>();
        inventory.put("book", Tax.ESSENTIALS);
        inventory.put("imported bottle of perfume", Tax.NON_ESSENTIALS);
        inventory.put("music CD", Tax.NON_ESSENTIALS);
        parser = new CartParser(inventory);
    }

    @Test
    public void shouldParseEmptyCartFromEmptyInput() {
        Cart cart = parser.parse("");
        assertTrue(cart.isEmpty());
    }

    @Test
    public void shouldParseNonImportedTaxExemptProduct() {
        Cart cart = parser.parse("1 book at 12.49");

        CartItem cartItem = cart.getItems().get(0);
        assertEquals(1, cartItem.getQuantity());

        Product product = cartItem.getProduct();
        assertEquals("book", product.getName());
        assertEquals(12.49, product.getPrice(), 0);
        assertTrue(product.isTaxExempt());
        assertFalse(product.isImported());
    }

    @Test
    public void shouldParseNonImportedTaxedProduct() {
        Cart cart = parser.parse("1 music CD at 14.99");

        CartItem cartItem = cart.getItems().get(0);
        assertEquals(1, cartItem.getQuantity());

        Product product = cartItem.getProduct();
        assertEquals("music CD", product.getName());
        assertEquals(14.99, product.getPrice(), 0);
        assertFalse(product.isTaxExempt());
        assertFalse(product.isImported());
    }

    @Test
    public void shouldParseImportedTaxedProduct() {
        Cart cart = parser.parse("2 imported bottle of perfume at 27.99");

        CartItem cartItem = cart.getItems().get(0);
        assertEquals(2, cartItem.getQuantity());

        Product product = cartItem.getProduct();
        assertEquals("imported bottle of perfume", product.getName());
        assertEquals(27.99, product.getPrice(), 0);
        assertFalse(product.isTaxExempt());
        assertTrue(product.isImported());
    }
}
