package com.paynestsystem.app.payment;

import java.math.BigDecimal;
import java.time.YearMonth;


public class CardPayment extends PaymentMethod { // check if you can extend rather than implement PaymentMethod interface, since it is a class and not an interface

    private final String cardHolderName;
    private final String cardNumber;
    private final YearMonth expiryDate;
    private final char cvv;
    private enum cardType {Debit, Credit};

    @Override
    public String toString() {
        return "Card{" +
                "cardHolderName='" + cardHolderName + '\'' +
                ", cardNumber='" + mask(cardNumber) + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }

    @Override
    public boolean processPayment(BigDecimal amount) {
        validateAmount(amount);
        System.out.println("Processing card payment of R" + String.format("%.2f", amount));
        return true; // Simulate successful payment
    }

    @Override
    public String getPaymentType() {
        return "Card";
    }

    private String mask(String number) {
        if (number == null || number.length() < 4) {
            return "**** **** **** ****";
        }
        return "**** **** **** " + number.substring(number.length() - 4);
    }

    public CardPayment(String cardHolderName, String cardNumber, YearMonth expiryDate, char cvv) {

        if (cardHolderName == null || cardHolderName.isBlank()) {
            throw new IllegalArgumentException("Card holder name cannot be blank.");
        }
        if (cardNumber == null || cardNumber.isBlank() || cardNumber.length() < 1) {
            throw new IllegalArgumentException("Card number cannot be blank and must be greater than zero.");
        }
        if (expiryDate == null || !expiryDate.isAfter(YearMonth.now())) {
            throw new IllegalArgumentException("Expiry date cannot be blank and must be a valid future date.");
        }
        if (cvv <= 0) {
            throw new IllegalArgumentException("CVV must be greater than zero.");
        }

        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

        public String getCardHolderName() {
            return cardHolderName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public YearMonth getExpiryDate() {
            return expiryDate;
        }

        public char getCvv() {
            return cvv;
        }

        public cardType getCardType() {
            return cardType.Debit; // Assuming all card payments are debit for this example
        }
    }



