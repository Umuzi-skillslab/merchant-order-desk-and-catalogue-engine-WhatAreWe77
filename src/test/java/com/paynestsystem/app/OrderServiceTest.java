package com.paynestsystem.app;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.paynestsystem.app.domain.Customer;
import com.paynestsystem.app.domain.Order;
import com.paynestsystem.app.domain.Product;
import com.paynestsystem.app.service.OrderService;
import com.paynestsystem.app.service.OrderSummary;

public class OrderServiceTest {

    @Test
    public void testCreateOrderSummary() {
        Product smartphone = new Product(1, "Samsung Galaxy S26 Ultra", BigDecimal.valueOf(27999.99));
        Product smartwatch = new Product(2, "Samsung Galaxy Watch 6 Classic", BigDecimal.valueOf(4999.99));

        Customer customer = new Customer(1, "Rele-Mbokazi Mkhize", "mbokazimkhize@yahoo.co.za");
        Order order = new Order(1, customer);
        order.addItem(smartphone, 1);
        order.addItem(smartwatch, 3);

        OrderService orderService = new OrderService();
        OrderSummary summary = orderService.createOrderSummary(order);

        assertEquals(1, summary.getOrderId());
        assertEquals("Rele-Mbokazi Mkhize", summary.getCustomerName());
        assertEquals("mbokazimkhize@yahoo.co.za", summary.getCustomerEmail());
        assertEquals(2, summary.getItems().size());
        assertEquals(BigDecimal.valueOf(27999.99).setScale(2), summary.getItems().get(0).getPricePerUnit());
        assertEquals(BigDecimal.valueOf(4999.99).setScale(2), summary.getItems().get(1).getPricePerUnit());
        assertEquals(BigDecimal.valueOf(42999.96).setScale(2), summary.getTotalAmount());
    }

    @Test
    public void testCreateOrderSummaryRejectsNullOrder() {
        OrderService orderService = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> orderService.createOrderSummary(null));
    }

    @Test
    public void testOrderSummaryImmutableLineItems() {
        Product smartphone = new Product(1, "Samsung Galaxy S26 Ultra", BigDecimal.valueOf(27999.99));
        Customer customer = new Customer(1, "Rele-Mbokazi Mkhize", "mbokazimkhize@yahoo.co.za");
        Order order = new Order(1, customer);
        order.addItem(smartphone, 1);

        OrderService orderService = new OrderService();
        OrderSummary summary = orderService.createOrderSummary(order);

        assertEquals(1, summary.getItems().size());
        assertThrows(UnsupportedOperationException.class, () -> summary.getItems().add(summary.getItems().get(0)));
    }
}
