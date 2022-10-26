package dojo.supermarket.model;

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
        if (offer.offerType == SpecialOfferType.THREE_FOR_TWO) {
            return getDiscountForThreeForTwo(product, quantity, unitPrice, quantityAsInt);
        } else if (offer.offerType == SpecialOfferType.TWO_FOR_AMOUNT) {
            return getDiscountForTwoForAmount(product, quantity, offer, unitPrice, quantityAsInt);
        } else if (offer.offerType == SpecialOfferType.FIVE_FOR_AMOUNT) {
            return getDiscountForFiveForAmount(product, quantity, offer, unitPrice, quantityAsInt);
        } else if (offer.offerType == SpecialOfferType.TEN_PERCENT_DISCOUNT) {
            return getDiscountForTenPercent(product, quantity, offer, unitPrice);
        }
        return null;
    }

    private Discount getDiscountForTenPercent(Product product, double quantity, Offer offer, double unitPrice) {
        return new Discount(product, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
    }

    private Discount getDiscountForFiveForAmount(Product product, double quantity, Offer offer, double unitPrice, int quantityAsInt) {
        int minimumAmountForOffer;
        minimumAmountForOffer = 5;
        int numberOfXs = quantityAsInt / minimumAmountForOffer;
        if (quantityAsInt >= 5) {
            double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
            return new Discount(product, minimumAmountForOffer + " for " + offer.argument, -discountTotal);
        }
        return null;
    }

    private Discount getDiscountForTwoForAmount(Product product, double quantity, Offer offer, double unitPrice, int quantityAsInt) {
        int minimumAmountForOffer;
        minimumAmountForOffer = 2;
        if (quantityAsInt >= 2) {
            return getDiscountForTwoForAmount(product, quantity, offer, unitPrice, quantityAsInt, minimumAmountForOffer);
        }
        return null;
    }

    private Discount getDiscountForThreeForTwo(Product product, double quantity, double unitPrice, int quantityAsInt) {
        int minimumAmountForOffer;
        minimumAmountForOffer = 3;
        int numberOfXs = quantityAsInt / minimumAmountForOffer;
        if (quantityAsInt > 2) {
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            return new Discount(product, "3 for 2", -discountAmount);
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
