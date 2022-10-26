package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class TenPercentPolicy implements DiscountPolicy {
    @Override
    public Discount getDiscount(Product product, double quantity, double unitPrice, int quantityAsInt, Offer offer) {
        return new Discount(product, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
    }
}
