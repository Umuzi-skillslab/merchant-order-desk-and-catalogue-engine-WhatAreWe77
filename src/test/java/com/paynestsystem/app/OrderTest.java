package com.paynestsystem.app;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.paynestsystem.app.domain.Customer;
import com.paynestsystem.app.domain.Order;
import com.paynestsystem.app.domain.Product;

public class OrderTest {

    @Test
    public void testOrderCreationAndTotalCalculation() {
        Product smartphone = new Product(1, "Samsung Galaxy S26 Ultra", new BigDecimal("27999.99"));
        Product smartwatch = new Product(2, "Samsung Galaxy Watch 6 Classic", new BigDecimal("4999.99"));

        Customer customer = new Customer(1, "Rele-Mbokazi Mkhize", "mbokazimkhize@yahoo.co.za");
        Order order = new Order(1, customer);

        assertNotNull(order, "Order should be created successfully");
        assertEquals(1, order.getId());
        assertEquals("Rele-Mbokazi Mkhize", order.getCustomer().getName());

        order.addItem(smartphone, 1);
        order.addItem(smartwatch, 3);

        assertEquals(2, order.getItems().size(), "Order should contain two items");
        assertEquals("Samsung Galaxy S26 Ultra", order.getItems().get(0).getProduct().getName());
        assertEquals("Samsung Galaxy Watch 6 Classic", order.getItems().get(1).getProduct().getName());

        assertEquals(new BigDecimal("42999.96"), order.calculateTotal());
    }
}
