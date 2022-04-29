package org.salestaxes.shopping;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private List<ReceiptItem> items =  new ArrayList<>();
    private String totalCost;
    private String saleTaxes;

    public Receipt(List<ReceiptItem> items, String totalCost, String saleTaxes) {
        this.items = items;
        this.totalCost = totalCost;
        this.saleTaxes = saleTaxes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ReceiptItem item : items) {
            sb.append(item.toString());
        }
        sb.append("Sale taxes: " + saleTaxes + "\n");
        sb.append("Total: " + totalCost + "\n");

        return sb.toString();
    }
}
