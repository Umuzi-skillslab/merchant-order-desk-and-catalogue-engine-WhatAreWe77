package com.paynestsystem.app.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int id;
    private final Customer customer;
    private final List<OrderItem> items;

    public Order(int id, Customer customer) {
        if (id <= 0) {
            throw new IllegalArgumentException("Order id must be greater than zero.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product cannot be null and quantity must be greater than zero.");
        }
        items.add(new OrderItem(product, quantity));
    }

    public BigDecimal calculateTotal() {
        return items.stream()
            .map(OrderItem::calculateTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }
}

