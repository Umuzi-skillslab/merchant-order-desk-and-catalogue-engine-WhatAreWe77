package com.paynestsystem.app.domain;

import java.math.BigDecimal;

public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Order item product cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Order item quantity must be greater than zero.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal calculateTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
