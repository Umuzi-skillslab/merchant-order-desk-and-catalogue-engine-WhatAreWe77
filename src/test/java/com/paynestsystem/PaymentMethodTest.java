package com.paynestsystem;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.paynestsystem.app.payment.BuyNowPayLater;
import com.paynestsystem.app.payment.CardPayment;
import com.paynestsystem.app.payment.EftPayment;
import com.paynestsystem.app.payment.PaymentMethod;
import com.paynestsystem.app.payment.WalletPayment;

class PaymentMethodTest {
    @Test
    void checkoutCanUseDifferentPaymentMethods() {
        PaymentMethod card = new CardPayment("Rele-Mbokazi Mkhize", "CARD123", YearMonth.of(2027, 12), "123".charAt(0));
        WalletPayment wallet = new WalletPayment(
            "WALLET123", "PayNest Wallet", "Rele-Mbokazi Mkhize", BigDecimal.valueOf(20000));

        assertTrue(card.processPayment(BigDecimal.valueOf(12400)));
        assertTrue(wallet.processPayment(BigDecimal.valueOf(12400)));
        assertEquals("Card", card.getPaymentType());
        assertEquals("Wallet", wallet.getPaymentType());
        assertEquals(BigDecimal.valueOf(7600), wallet.getBalance());
    }

    @Test
    void paymentRailsRejectZeroAndNegativeAmounts() {
        List<PaymentMethod> rails = List.of(
            new CardPayment("Kamohelo", "CARD123", YearMonth.now().plusMonths(1), '1'),
            new WalletPayment("WALLET123", "PayNest Wallet", "Customer", BigDecimal.valueOf(20000)),
            new EftPayment("Ubuntu Bank", "Komahelo", "123456789", "123456"),
            new BuyNowPayLater("PayNest", "Komahelo", "1234567890123", "kamzadesmall@example.com"));

        for (PaymentMethod rail : rails) {
            assertThrows(IllegalArgumentException.class, () -> rail.processPayment(BigDecimal.ZERO));
            assertThrows(IllegalArgumentException.class,
                () -> rail.processPayment(BigDecimal.valueOf(-1)));
        }
    }

    @Test
    void buyNowPayLaterRequiresAThirteenDigitNumericId() {
        assertThrows(IllegalArgumentException.class, () -> new BuyNowPayLater(
            "PayNest", "Customer", "123456789012X", "customer@example.com"));
    }
}
