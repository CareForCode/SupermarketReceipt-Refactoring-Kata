package dojo.supermarket.model;

import java.util.*;
import java.util.stream.Collectors;

public class Items {
    private final List<ProductQuantity> items = new ArrayList<>();

    public Items() {
    }

    List<ProductQuantity> getItems() {
        return Collections.unmodifiableList(items);
    }

    void addItem(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
    }

    double getQuantity(Product product) {
        return items.stream()
                .filter(productQuantity -> productQuantity.getProduct().equals(product))
                .mapToDouble(ProductQuantity::getQuantity)
                .sum();
    }

    Map<Product,Double> getProductQuantities() {
        return Collections.unmodifiableMap(items.stream()
                .map(ProductQuantity::getProduct)
                .distinct()
                .collect(Collectors.toMap(product -> product, this::getQuantity)));
    }
}