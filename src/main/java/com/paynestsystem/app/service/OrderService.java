package com.paynestsystem.app.service;
import java.util.ArrayList;
import java.util.List;

import com.paynestsystem.app.domain.Order;
import com.paynestsystem.app.domain.OrderItem;

public class OrderService {

    public OrderSummary createOrderSummary(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        List<OrderSummary.LineItem> lineItems = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            lineItems.add(new OrderSummary.LineItem(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getProduct().getPrice()));
        }

        return new OrderSummary(
            order.getId(),
            order.getCustomer().getName(),
            order.getCustomer().getEmail(),
            List.copyOf(lineItems));
    }
}
