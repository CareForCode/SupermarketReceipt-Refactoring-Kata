package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class ThreeForTwoPolicy implements DiscountPolicy {
    @Override
    public Discount getDiscount(Product product, double quantity, double unitPrice, int quantityAsInt, Offer offer) {
        int minimumAmountForOffer = 3;
        int numberOfXs = quantityAsInt / minimumAmountForOffer;
        if (quantityAsInt > 2) {
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            return new Discount(product, "3 for 2", -discountAmount);
        }
        return null;
    }
}
