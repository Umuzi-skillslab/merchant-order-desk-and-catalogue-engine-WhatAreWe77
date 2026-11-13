package com.paynestsystem.app;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.paynestsystem.app.domain.Customer;
import com.paynestsystem.app.domain.Order;
import com.paynestsystem.app.domain.Product;
import com.paynestsystem.app.payment.PaymentMethod;
import com.paynestsystem.app.payment.WalletPayment;
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
    public void testCheckoutCalculatesTotalOnceAndReturnsPaymentResult() {
        Customer customer = new Customer(1, "Sizwe", "siztheworld@example.com");
        CountingOrder order = new CountingOrder(1, customer);
        order.addItem(new Product(1, "Product", BigDecimal.valueOf(10.25)), 2);

        RecordingPaymentMethod paymentMethod = new RecordingPaymentMethod(false);

        boolean result = new OrderService().checkout(order, paymentMethod);

        assertFalse(result);
        assertEquals(1, order.getTotalCalculationCount());
        assertEquals(BigDecimal.valueOf(20.50).setScale(2), paymentMethod.getProcessedAmount());
        assertEquals(1, paymentMethod.getProcessCallCount());
    }

    @Test
    public void testCheckoutRejectsNullArguments() {
        OrderService orderService = new OrderService();
        Customer customer = new Customer(1, "Alina", "AlinaSek@example.com");
        Order order = new Order(1, customer);
        order.addItem(new Product(1, "Product", BigDecimal.ONE), 1);
        PaymentMethod paymentMethod = new RecordingPaymentMethod(true);

        assertThrows(IllegalArgumentException.class, () -> orderService.checkout(null, paymentMethod));
        assertThrows(IllegalArgumentException.class, () -> orderService.checkout(order, null));
    }

    @Test
    public void testCheckoutReturnsFalseWhenWalletBalanceIsInsufficient() {
        Customer customer = new Customer(1, "Makamohelo", "MakamzaSek@example.com");
        Order order = new Order(1, customer);
        order.addItem(new Product(1, "Product", BigDecimal.valueOf(20.50)), 1);
        WalletPayment wallet = new WalletPayment(
            "WALLET123", "PayNest Wallet", customer.getName(), BigDecimal.valueOf(20.49));

        assertFalse(new OrderService().checkout(order, wallet));
        assertEquals(BigDecimal.valueOf(20.49), wallet.getBalance());
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

    private static class CountingOrder extends Order {
        private int totalCalculationCount;

        CountingOrder(int id, Customer customer) {
            super(id, customer);
        }

        @Override
        public BigDecimal calculateTotal() {
            totalCalculationCount++;
            return super.calculateTotal();
        }

        int getTotalCalculationCount() {
            return totalCalculationCount;
        }
    }

    private static class RecordingPaymentMethod extends PaymentMethod {
        private final boolean result;
        private BigDecimal processedAmount;
        private int processCallCount;

        RecordingPaymentMethod(boolean result) {
            this.result = result;
        }

        @Override
        public boolean processPayment(BigDecimal amount) {
            processedAmount = amount;
            processCallCount++;
            return result;
        }

        @Override
        public String getPaymentType() {
            return "Test";
        }

        BigDecimal getProcessedAmount() {
            return processedAmount;
        }

        int getProcessCallCount() {
            return processCallCount;
        }
    }
}
