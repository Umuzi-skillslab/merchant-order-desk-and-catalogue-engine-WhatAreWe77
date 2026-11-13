package com.paynestsystem.app.service;

import java.math.BigDecimal;
import java.util.List;

public class OrderSummary {
    private final int orderId;
    private final String customerName;
    private final String customerEmail;
    private final List<LineItem> items;

    public OrderSummary(int orderId, String customerName, String customerEmail, List<LineItem> items) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order id must be greater than zero.");
        }
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be blank.");
        }
        if (customerEmail == null || customerEmail.isBlank()) {
            throw new IllegalArgumentException("Customer email cannot be blank.");
        }
        if (items == null) {
            throw new IllegalArgumentException("Order summary items cannot be null.");
        }
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.items = List.copyOf(items);
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public BigDecimal getTotalAmount() {
        return items.stream()
            .map(LineItem::getLineTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static final class LineItem {
        private final int productId;
        private final String productName;
        private final int quantity;
        private final BigDecimal pricePerUnit;

        public LineItem(int productId, String productName, int quantity, BigDecimal pricePerUnit) {
            if (productId <= 0) {
                throw new IllegalArgumentException("Product id must be greater than zero.");
            }
            if (productName == null || productName.isBlank()) {
                throw new IllegalArgumentException("Product name cannot be blank.");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero.");
            }
            if (pricePerUnit == null || pricePerUnit.signum() < 0) {
                throw new IllegalArgumentException("Price per unit cannot be null or negative.");
            }
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.pricePerUnit = pricePerUnit;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getPricePerUnit() {
            return pricePerUnit;
        }

        public BigDecimal getLineTotal() {
            return pricePerUnit.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
