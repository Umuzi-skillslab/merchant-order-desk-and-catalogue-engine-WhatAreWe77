package com.paynestsystem.app;

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
    }
}
