package com.paynestsystem.app.payment;
import java.math.BigDecimal;

public class WalletPayment extends PaymentMethod {

    private final String walletId;
    private final String walletProvider;
    private final String walletOwnerName;
    private enum WalletType {Debit, Credit}

    @Override
    public boolean processPayment(BigDecimal amount) {
        System.out.println("Processing wallet payment of R" + String.format("%.2f", amount));
        return true; // Simulate successful payment
    }

    @Override
    public String getPaymentType() {
        return "Wallet";
    }

    public WalletPayment(String walletId, String walletProvider, String walletOwnerName) {

        if (walletId == null || walletId.isBlank()) {
            throw new IllegalArgumentException("Wallet ID cannot be blank.");
        }

        if (walletProvider == null || walletProvider.isBlank()) {
            throw new IllegalArgumentException("Wallet provider cannot be blank.");
        }

        if (walletOwnerName == null || walletOwnerName.isBlank()) {
            throw new IllegalArgumentException("Wallet owner name cannot be blank.");
        }

        this.walletId = walletId;
        this.walletProvider = walletProvider;
        this.walletOwnerName = walletOwnerName;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getWalletProvider() {
        return walletProvider;
    }

    public String getWalletOwnerName() {
        return walletOwnerName;
    }

    public WalletType getWalletType() {
        return WalletType.Debit; // Assuming all wallet payments are debit for this example
    }
}
