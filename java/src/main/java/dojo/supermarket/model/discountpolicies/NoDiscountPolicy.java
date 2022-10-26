package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public Discount getDiscount(Product product, double quantity, double unitPrice, int quantityAsInt, Offer offer) {
        return null;
    }
}
