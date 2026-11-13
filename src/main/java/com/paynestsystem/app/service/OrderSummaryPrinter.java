package com.paynestsystem.app.service;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderSummaryPrinter {
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));

    public void print(OrderSummary summary) {
        if (summary == null) {
            throw new IllegalArgumentException("Order summary cannot be null.");
        }

        System.out.println("PayNest Invoice Summary");
        System.out.println("Order ID: " + summary.getOrderId());
        System.out.println("Customer : " + summary.getCustomerName());
        System.out.println("Email  : " + summary.getCustomerEmail());
        System.out.println("Items Ordered:");

        if (summary.getItems().isEmpty()) {
            System.out.println("No items were ordered.");
        } else {
            for (OrderSummary.LineItem item : summary.getItems()) {
                System.out.println("- " + item.getProductName() + " x " + item.getQuantity()
                    + " @ " + CURRENCY_FORMATTER.format(item.getPricePerUnit())
                    + " = " + CURRENCY_FORMATTER.format(item.getLineTotal()));
            }
        }

        System.out.println("Total Amount: " + CURRENCY_FORMATTER.format(summary.getTotalAmount()));
    }
}
