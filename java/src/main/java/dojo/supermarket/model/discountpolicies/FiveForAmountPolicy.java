package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class FiveForAmountPolicy implements DiscountPolicy {

    private final Product product;
    private final double quantity;
    private final double unitPrice;
    private final int quantityAsInt;
    private final Offer offer;

    public FiveForAmountPolicy(Product product, double quantity, double unitPrice, Offer offer) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.offer = offer;
        this.quantityAsInt = (int) quantity;
    }

    @Override
    public Discount getDiscount() {
        int minimumAmountForOffer = 5;
        int numberOfXs = this.quantityAsInt / minimumAmountForOffer;
        if (this.quantityAsInt >= 5) {
            double discountTotal = this.unitPrice * this.quantity - (this.offer.argument * numberOfXs + this.quantityAsInt % 5 * this.unitPrice);
            return new Discount(this.product, minimumAmountForOffer + " for " + this.offer.argument, -discountTotal);
        }
        return null;
    }
}
