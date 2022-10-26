package dojo.supermarket.model;

import java.util.List;

public class Receipt {

    public final ReceiptItems receiptItems = new ReceiptItems();
    public final Discounts discounts = new Discounts();

    public double getTotalPrice() {
        double total = 0.0;
        total = receiptItems.getTotal(total);
        total = discounts.getTotalDiscounts(total);
        return total;
    }

    public List<ReceiptItem> getItems() {
        return receiptItems.getItems();
    }

    public List<Discount> getDiscounts() {
        return discounts.getDiscounts();
    }
}
