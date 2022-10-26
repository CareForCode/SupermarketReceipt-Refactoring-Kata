package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class TwoForAmountPolicy implements DiscountPolicy {

    private final Product product;
    private final double quantity;
    private final double unitPrice;
    private final int quantityAsInt;
    private final Offer offer;

    public TwoForAmountPolicy(Product product, double quantity, double unitPrice, double quantityAsInt, Offer offer) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.offer = offer;
        this.quantityAsInt = (int) quantity;
    }

    @Override
    public Discount getDiscount(Product product, double quantity, double unitPrice, int quantityAsInt, Offer offer) {
        int minimumAmountForOffer = 2;
        if (this.quantityAsInt >= 2) {
            Discount discount;
            int intDivision = this.quantityAsInt / minimumAmountForOffer;
            double pricePerUnit = this.offer.argument * intDivision;
            double theTotal = (this.quantityAsInt % 2) * this.unitPrice;
            double total = pricePerUnit + theTotal;
            double discountN = this.unitPrice * this.quantity - total;
            discount = new Discount(this.product, "2 for " + this.offer.argument, -discountN);
            return discount;
        }
        return null;
    }

}
