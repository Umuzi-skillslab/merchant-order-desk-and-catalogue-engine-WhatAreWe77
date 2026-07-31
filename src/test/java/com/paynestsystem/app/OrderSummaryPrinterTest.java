package com.paynestsystem.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderSummaryPrinterTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintFormatsCurrencyAndSummaryLines() {
        Product smartphone = new Product(1, "Samsung Galaxy S26 Ultra", new BigDecimal("27999.99"));
        Customer customer = new Customer(1, "Rele-Mbokazi Mkhize", "mbokazimkhize@yahoo.co.za");
        Order order = new Order(1, customer);
        order.addItem(smartphone, 1);

        OrderService orderService = new OrderService();
        OrderSummary summary = orderService.createOrderSummary(order);

        OrderSummaryPrinter printer = new OrderSummaryPrinter();
        printer.print(summary);

        String output = outputStream.toString();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));

        assertTrue(output.contains("PayNest Invoice Summary"));
        assertTrue(output.contains("Order ID: 1"));
        assertTrue(output.contains("Customer : Rele-Mbokazi Mkhize"));
        assertTrue(output.contains("Email  : mbokazimkhize@yahoo.co.za"));
        assertTrue(output.contains("- Samsung Galaxy S26 Ultra x 1 @ " + currencyFormatter.format(new BigDecimal("27999.99")) + " = " + currencyFormatter.format(new BigDecimal("27999.99"))));
        assertTrue(output.contains("Total Amount: " + currencyFormatter.format(new BigDecimal("27999.99"))));
    }
}
