package com.paynestsystem.app.payment;
import java.math.BigDecimal;

public class EftPayment extends PaymentMethod {

    private final String bankName;
    private final String accountHolderName;
    private final String accountNumber;
    private final String branchCode;

    @Override
    public boolean processPayment(BigDecimal amount) {
        validateAmount(amount);
        System.out.println("Processing EFT payment of R" + String.format("%.2f", amount));
        return true; // Simulate successful payment
    }

    @Override
    public String getPaymentType() {
        return "EFT";
    }

    public EftPayment (String bankName, String accountHolderName, String accountNumber, String branchCode) {

        if (bankName == null || bankName.isBlank()) {
            throw new IllegalArgumentException("Bank name cannot be blank.");
        }

        if (accountHolderName == null || accountHolderName.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be blank.");
        }

        if (accountNumber == null || accountNumber.isBlank() || accountNumber.length() < 1) {
            throw new IllegalArgumentException("Account number cannot be blank and must be greater than zero.");
        }

        if (branchCode == null || branchCode.isBlank() || branchCode.length() < 1) {
            throw new IllegalArgumentException("Branch code must be greater than zero.");
        }

        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.branchCode = branchCode;
    }

    public String getName(){
        return bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }
}
