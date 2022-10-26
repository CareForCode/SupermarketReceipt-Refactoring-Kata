package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private final List<Discount> discounts = new ArrayList<>();
    public final ReceiptItems receiptItems = new ReceiptItems();

    public double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : receiptItems.getItems()) {
            total += item.getTotalPrice();
        }
        for (Discount discount : discounts) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    public List<ReceiptItem> getItems() {
        return receiptItems.getItems();
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }
}
