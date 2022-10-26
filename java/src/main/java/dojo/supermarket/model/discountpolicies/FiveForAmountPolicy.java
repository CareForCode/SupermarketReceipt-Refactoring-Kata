package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class FiveForAmountPolicy implements DiscountPolicy {
    @Override
    public Discount getDiscount(Product product, double quantity, double unitPrice, int quantityAsInt, Offer offer) {
        int minimumAmountForOffer = 5;
        int numberOfXs = quantityAsInt / minimumAmountForOffer;
        if (quantityAsInt >= 5) {
            double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
            return new Discount(product, minimumAmountForOffer + " for " + offer.argument, -discountTotal);
        }
        return null;
    }
}
