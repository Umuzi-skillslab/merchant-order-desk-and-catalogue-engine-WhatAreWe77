package com.paynestsystem.app;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.paynestsystem.app.domain.Customer;
import com.paynestsystem.app.domain.Order;
import com.paynestsystem.app.domain.Product;

public class OrderItemTest {

    @Test
    public void testOrderItemsAndTotalForMyProducts() {
        Product smartphone = new Product(1, "Samsung Galaxy S26 Ultra", new BigDecimal("27999.99"));
        Product smartwatch = new Product(2, "Samsung Galaxy Watch 6 Classic", new BigDecimal("4999.99"));

        Customer customer = new Customer(1, "Rele-Mbokazi Mkhize", "mbokazimkhize@yahoo.co.za");
        Order order = new Order(1, customer);

        order.addItem(smartphone, 1);
        order.addItem(smartwatch, 3);

        assertEquals(2, order.getItems().size(), "Order should contain two ordered items");
        assertEquals("Samsung Galaxy S26 Ultra", order.getItems().get(0).getProduct().getName());
        assertEquals(1, order.getItems().get(0).getQuantity());

        assertEquals("Samsung Galaxy Watch 6 Classic", order.getItems().get(1).getProduct().getName());
        assertEquals(3, order.getItems().get(1).getQuantity());

        assertEquals(new BigDecimal("42999.96"), order.calculateTotal(), "Order total should match the expected value");
    }
}
