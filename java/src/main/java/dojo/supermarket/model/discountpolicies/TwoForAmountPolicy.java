package dojo.supermarket.model.discountpolicies;

import dojo.supermarket.model.Discount;
import dojo.supermarket.model.Offer;
import dojo.supermarket.model.Product;

public class TwoForAmountPolicy implements DiscountPolicy {

    @Override
    public Discount getDiscount(Product product, double quantity, double unitPrice, int quantityAsInt, Offer offer) {
        int minimumAmountForOffer = 2;
        if (quantityAsInt >= 2) {
            return getDiscountForTwoForAmount(product, quantity, offer, unitPrice, quantityAsInt, minimumAmountForOffer);
        }
        return null;
    }

    private Discount getDiscountForTwoForAmount(Product product, double quantity, Offer offer, double unitPrice, int quantityAsInt, int x) {
        Discount discount;
        int intDivision = quantityAsInt / x;
        double pricePerUnit = offer.argument * intDivision;
        double theTotal = (quantityAsInt % 2) * unitPrice;
        double total = pricePerUnit + theTotal;
        double discountN = unitPrice * quantity - total;
        discount = new Discount(product, "2 for " + offer.argument, -discountN);
        return discount;
    }
}
