package com.paynestsystem.app.app;
import com.paynestsystem.app.domain.Customer;
import com.paynestsystem.app.domain.Order;
import com.paynestsystem.app.domain.Product;
import com.paynestsystem.app.service.OrderService;
import com.paynestsystem.app.service.OrderSummary;
import com.paynestsystem.app.service.OrderSummaryPrinter;
import com.paynestsystem.app.payment.PaymentProcessor;
import com.paynestsystem.app.payment.PaymentMethod;
import com.paynestsystem.app.payment.CardPayment;

import java.time.YearMonth;

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

        PaymentMethod paymentMethod = new CardPayment(
            customer.getName(),
            "DEMO-CARD-1234",
            YearMonth.now().plusYears(2),
            '7'
        );
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        paymentProcessor.processPayment(paymentMethod, order.calculateTotal());

    }
}
