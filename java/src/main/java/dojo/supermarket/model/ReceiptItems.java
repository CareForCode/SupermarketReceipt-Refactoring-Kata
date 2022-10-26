package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptItems {
    final List<ReceiptItem> items = new ArrayList<>();

    public ReceiptItems() {
    }

    public List<ReceiptItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addProduct(Product p, double quantity, double price, double totalPrice) {
        items.add(new ReceiptItem(p, quantity, price, totalPrice));
    }

    double getTotal(double total) {
        for (ReceiptItem item : getItems()) {
            total += item.getTotalPrice();
        }
        return total;
    }
}