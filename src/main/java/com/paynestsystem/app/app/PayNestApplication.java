package com.paynestsystem.app.app;
import java.math.BigDecimal;

import com.paynestsystem.app.domain.Customer;
import com.paynestsystem.app.domain.Order;
import com.paynestsystem.app.domain.Product;
import com.paynestsystem.app.payment.PaymentMethod;
import com.paynestsystem.app.payment.WalletPayment;
import com.paynestsystem.app.service.OrderService;
import com.paynestsystem.app.service.OrderSummary;
import com.paynestsystem.app.service.OrderSummaryPrinter;

public class PayNestApplication {
    public static void main(String[] args) {
        Product smartphone = new Product(1, "Samsung Galaxy S26 Ultra", 27999.99);
        Product smartwatch = new Product(2, "Samsung Galaxy Watch 6 Classic", 4999.99);

        Customer customer = new Customer(1, "Rele-Mbokazi Mkhize", "mbokazimkhize@yahoo.co.za");

        Order order = new Order(1, customer);
        order.addItem(smartphone, 1);
        order.addItem(smartwatch, 3);

        OrderService orderService = new OrderService();
        OrderSummary orderSummary = orderService.createOrderSummary(order);

        OrderSummaryPrinter printer = new OrderSummaryPrinter();
        printer.print(orderSummary);

        WalletPayment walletPayment = new WalletPayment(
            "DEMO-WALLET-1234",
            "PayNest Wallet",
            customer.getName(),
            new BigDecimal("50000.00")
        );
        PaymentMethod paymentMethod = walletPayment;
        System.out.println("Wallet balance: R" + String.format("%.2f", walletPayment.getBalance()));
        orderService.checkout(order, paymentMethod);
        System.out.println("Wallet balance: R" + String.format("%.2f", walletPayment.getBalance()));

    }
}
