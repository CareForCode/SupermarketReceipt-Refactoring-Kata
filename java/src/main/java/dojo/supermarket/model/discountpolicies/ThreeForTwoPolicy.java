package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class ThreeForTwoPolicy implements DiscountPolicy {

    private final Product product;
    private final double quantity;
    private final double unitPrice;
    private final int quantityAsInt;

    public ThreeForTwoPolicy(Product product, double quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.quantityAsInt = (int) quantity;
    }

    @Override
    public Discount getDiscount(Product product, double quantity, double unitPrice, int quantityAsInt, Offer offer) {
        int minimumAmountForOffer = 3;
        int numberOfXs = this.quantityAsInt / minimumAmountForOffer;
        if (this.quantityAsInt > 2) {
            double discountAmount = this.quantity * this.unitPrice - ((numberOfXs * 2 * this.unitPrice) + this.quantityAsInt % 3 * this.unitPrice);
            return new Discount(this.product, "3 for 2", -discountAmount);
        }
        return null;
    }
}
