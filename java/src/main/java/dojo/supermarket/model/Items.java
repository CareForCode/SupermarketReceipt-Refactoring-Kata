package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Items {
    final List<ProductQuantity> items = new ArrayList<>();

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
}