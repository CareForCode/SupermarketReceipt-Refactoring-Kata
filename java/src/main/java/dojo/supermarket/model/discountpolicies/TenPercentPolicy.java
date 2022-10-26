package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class TenPercentPolicy implements DiscountPolicy {

    private final Product product;
    private final double quantity;
    private final double unitPrice;
    private final Offer offer;

    public TenPercentPolicy(Product product, double quantity, double unitPrice, Offer offer) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.offer = offer;
    }

    @Override
    public Discount getDiscount() {
        return new Discount(this.product, this.offer.argument + "% off", -this.quantity * this.unitPrice * this.offer.argument / 100.0);
    }
}
