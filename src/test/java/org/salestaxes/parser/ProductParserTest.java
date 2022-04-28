package org.salestaxes.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.salestaxes.Product;

public class ProductParserTest {

    private ProductParser parser;

    @Before
    public void init() {
        parser = new ProductParser();
    }

    @Test
    public void shouldParseNoProductFromEmptyInput() {
        Product product = parser.parse("");
        assertNull(product);
    }

    @Test
    public void shouldParseNonImportedTaxExemptProduct() {
        Product product = parser.parse("1 book at 12.49");
        assertEquals("book", product.getName());
        assertEquals(12.49, product.getPrice(), 0);
        assertTrue(product.isTaxExempt());
        assertFalse(product.isImported());
    }
}
