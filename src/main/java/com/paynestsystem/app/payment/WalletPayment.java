package com.paynestsystem.app.payment;
import java.math.BigDecimal;

public class WalletPayment extends PaymentMethod {

    private final String walletId;
    private final String walletProvider;
    private final String walletOwnerName;
    private  BigDecimal balance;
    private enum WalletType {Debit, Credit}

    @Override
    public boolean processPayment(BigDecimal amount) {
        validateAmount(amount);
        System.out.println("Processing wallet payment of R" + String.format("%.2f", amount));
        if (amount.compareTo(balance) > 0) {
            return false;
        }

        balance = balance.subtract(amount);
        return true;
    }

    @Override
    public String getPaymentType() {
        return "Wallet";
    }

    public WalletPayment(String walletId, String walletProvider, String walletOwnerName, BigDecimal balance) {

        if (walletId == null || walletId.isBlank()) {
            throw new IllegalArgumentException("Wallet ID cannot be blank.");
        }

        if (walletProvider == null || walletProvider.isBlank()) {
            throw new IllegalArgumentException("Wallet provider cannot be blank.");
        }

        if (walletOwnerName == null || walletOwnerName.isBlank()) {
            throw new IllegalArgumentException("Wallet owner name cannot be blank.");
        }
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Wallet balance cannot be null or negative.");
        }

        this.walletId = walletId;
        this.walletProvider = walletProvider;
        this.walletOwnerName = walletOwnerName;
        this.balance = balance;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public WalletType getWalletType() {
        return WalletType.Debit; // Assuming all wallet payments are debit for this example
    }
}
