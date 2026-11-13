package com.paynestsystem.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.paynestsystem.app.domain.Order;
import com.paynestsystem.app.domain.OrderItem;
import com.paynestsystem.app.payment.PaymentMethod;
import com.paynestsystem.app.payment.PaymentProcessor;

public class OrderService {

    public boolean checkout(Order order, PaymentMethod paymentMethod) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null.");
        }

        BigDecimal total = order.calculateTotal();
        return new PaymentProcessor().processPayment(paymentMethod, total);
    }

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
