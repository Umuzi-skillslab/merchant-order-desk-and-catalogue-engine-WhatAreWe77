package com.paynestsystem;

import java.math.BigDecimal;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.paynestsystem.app.payment.CardPayment;
import com.paynestsystem.app.payment.PaymentMethod;
import com.paynestsystem.app.payment.WalletPayment;

class PaymentMethodTest {
    @Test
    void checkoutCanUseDifferentPaymentMethods() {
        PaymentMethod card = new CardPayment("Rele-Mbokazi Mkhize", "CARD123", YearMonth.of(2027, 12), "123".charAt(0));
        PaymentMethod wallet = new WalletPayment( "WALLET123", "PayNest Wallet", "Rele-Mbokazi Mkhize");

        assertTrue(card.processPayment(BigDecimal.valueOf(12400)));
        assertTrue(wallet.processPayment(BigDecimal.valueOf(12400)));
        assertEquals("Card", card.getPaymentType());
        assertEquals("Wallet", wallet.getPaymentType());
    }
}
