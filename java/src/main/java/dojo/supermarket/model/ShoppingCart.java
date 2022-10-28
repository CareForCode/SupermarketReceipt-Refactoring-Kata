package dojo.supermarket.model;

import dojo.supermarket.model.discountpolicies.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final Items items = new Items();

    List<ProductQuantity> getItems() {
        return items.getItems();
    }

    void addItem(Product product) {
        addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return items.getProductQuantities();
    }

    public void addItemQuantity(Product product, double quantity) {
        items.addItem(product, quantity);
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product product: productQuantities().keySet()) {
            double quantityTest = items.getQuantity(product);
            if (offers.containsKey(product)) {
                applyOffer(receipt, catalog, product, quantityTest, offers.get(product));
            }
        }
    }

    private void applyOffer(Receipt receipt, SupermarketCatalog catalog, Product product, double quantity, Offer offer) {
        double unitPrice = catalog.getUnitPrice(product);
        DiscountPolicy discountPolicy = getDiscountPolicy(product, quantity, offer, unitPrice);
        if (discountPolicy.getDiscount() != null)
            receipt.discounts.addDiscount(discountPolicy.getDiscount());
    }

    private DiscountPolicy getDiscountPolicy(Product product, double quantity, Offer offer, double unitPrice) {
        if (offer.offerType == SpecialOfferType.THREE_FOR_TWO) {
            return new ThreeForTwoPolicy(product, quantity, unitPrice);
        } else if (offer.offerType == SpecialOfferType.TWO_FOR_AMOUNT) {
            return new TwoForAmountPolicy(product, quantity, unitPrice, offer);
        } else if (offer.offerType == SpecialOfferType.FIVE_FOR_AMOUNT) {
            return new FiveForAmountPolicy(product, quantity, unitPrice, offer);
        } else if (offer.offerType == SpecialOfferType.TEN_PERCENT_DISCOUNT) {
            return new TenPercentPolicy(product, quantity, unitPrice, offer);
        } else {
            return new NoDiscountPolicy();
        }
    }

    Receipt getReceipt(SupermarketCatalog catalog) {
        Receipt receipt = new Receipt();
        for (ProductQuantity pq: getItems()) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.receiptItems.addProduct(p, quantity, unitPrice, price);
        }
        return receipt;
    }

    Receipt getReceipt(Map<Product, Offer> offers, SupermarketCatalog catalog) {
        Receipt receipt = getReceipt(catalog);
        handleOffers(receipt, offers, catalog);
        return receipt;
    }
}
