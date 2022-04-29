package org.salestaxes.shopping;

import java.util.List;

public class Product {

    public enum Tax {
        ESSENTIALS(0),
        NON_ESSENTIALS(0.1),
        IMPORTED(0.05);

        public final double rate;

        Tax(double rate) {
            this.rate = rate;
        }
    }

    private final String name;
    private final double price;
    private final List<Tax> taxes;

    public Product(String name, double price, List<Tax> taxes) {
        this.name = name;
        this.price = price;
        this.taxes = taxes;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public boolean isTaxExempt() {
        return taxes.isEmpty() || Tax.ESSENTIALS.equals(taxes.get(0));
    }

    public boolean isImported() {
        return taxes.contains(Tax.IMPORTED);
    }

    public double getTaxRate() {
        double taxRate = 0;
        for (Tax tax : taxes) {
            taxRate += tax.rate;
        }
        return taxRate;
    }

    public double taxes() {
        return price * getTaxRate();
    }

    
}
