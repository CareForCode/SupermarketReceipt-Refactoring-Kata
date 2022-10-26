package dojo.supermarket.model;

import dojo.supermarket.model.discountpolicies.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    private final Map<Product, Double> productQuantities = new HashMap<>();

    List<ProductQuantity> getItems() {
        return Collections.unmodifiableList(items);
    }

    void addItem(Product product) {
        addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return Collections.unmodifiableMap(productQuantities);
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product product: productQuantities().keySet()) {
            double quantity = productQuantities.get(product);
            if (offers.containsKey(product)) {
                applyOffer(receipt, catalog, product, quantity, offers.get(product));
            }
        }
    }

    private void applyOffer(Receipt receipt, SupermarketCatalog catalog, Product product, double quantity, Offer offer) {
        double unitPrice = catalog.getUnitPrice(product);
        int quantityAsInt = (int) quantity;
        Discount discount = getDiscount(product, quantity, offer, unitPrice, quantityAsInt);
        if (discount != null)
            receipt.addDiscount(discount);
    }

    private Discount getDiscount(Product product, double quantity, Offer offer, double unitPrice, int quantityAsInt) {
        DiscountPolicy discountPolicy;
        if (offer.offerType == SpecialOfferType.THREE_FOR_TWO) {
            discountPolicy = new ThreeForTwoPolicy();
        } else if (offer.offerType == SpecialOfferType.TWO_FOR_AMOUNT) {
            discountPolicy = new TwoForAmountPolicy();
        } else if (offer.offerType == SpecialOfferType.FIVE_FOR_AMOUNT) {
            discountPolicy = new FiveForAmountPolicy();
        } else if (offer.offerType == SpecialOfferType.TEN_PERCENT_DISCOUNT) {
            discountPolicy = new TenPercentPolicy();
        } else {
            discountPolicy = new NoDiscountPolicy();
        }
        return discountPolicy.getDiscount(product, quantity, unitPrice, quantityAsInt, offer);
    }

}
