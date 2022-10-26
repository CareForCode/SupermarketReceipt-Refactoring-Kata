package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class Discounts {
    final List<Discount> discounts = new ArrayList<>();

    public Discounts() {
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    double getTotalDiscounts(double total) {
        for (Discount discount : getDiscounts()) {
            total += discount.getDiscountAmount();
        }
        return total;
    }
}