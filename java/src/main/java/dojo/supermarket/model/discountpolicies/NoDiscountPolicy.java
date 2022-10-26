package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;

public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public Discount getDiscount() {
        return null;
    }
}
