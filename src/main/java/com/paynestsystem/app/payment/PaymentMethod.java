package com.paynestsystem.app.payment;
import java.math.BigDecimal;

public abstract class PaymentMethod {    // use abstract class if you want to implement some methods and leave others to be implemented by subclasses
    public abstract boolean processPayment(BigDecimal amount);

    public abstract String getPaymentType();

    public void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }
}