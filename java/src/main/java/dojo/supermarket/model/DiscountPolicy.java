package dojo.supermarket.model;

public interface DiscountPolicy {
    Discount getDiscount(Product product, double quantity, double unitPrice, int quantityAsInt, Offer offer);
}
