package com.paynestsystem.app.payment;

import java.math.BigDecimal;

public class BuyNowPayLater extends PaymentMethod {

    private final String providerName;
    private final String accountHolderName;
    private final String accountOwnerId;
    private final String emailAddress;


    @Override
    public boolean processPayment(BigDecimal amount) {
        System.out.println("Processing Buy Now Pay Later payment of R" + String.format("%.2f", amount));
        return true; // Simulate successful payment
    }

    @Override
    public String getPaymentType() {
        return "Buy Now Pay Later";
    }

    public BuyNowPayLater(String providerName, String accountHolderName, String accountOwnerId, String emailAddress) {

        if (providerName == null || providerName.isBlank()) {
            throw new IllegalArgumentException("Provider name cannot be blank.");
        }

        if (accountHolderName == null || accountHolderName.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be blank.");
        }

        if (accountOwnerId  == null || accountOwnerId.length() !=13 || accountOwnerId.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Account owner ID must be 13 digits");
        }

        if (emailAddress == null || emailAddress.isBlank()) {
            throw new IllegalArgumentException("Email address cannot be blank.");
        }

        this.providerName = providerName;
        this.accountHolderName = accountHolderName;
        this.accountOwnerId = accountOwnerId;
        this.emailAddress = emailAddress;
    }

     public String getProviderName() {
        return providerName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountOwwnerId() {
        return accountOwnerId;
    }

    public String getEmailAdress() {
        return emailAddress;
    }
}
