# PayNest — Merchant Order Desk & Catalogue Engine

A Java console application for managing customers, products, and orders, with a sample payment-processing flow.

## Overview

PayNest models a simplified order management system. It creates an order for a customer, prints an invoice-style summary, and processes the order total through a selectable payment method. The demo application currently uses a card payment with placeholder details.

## Tech Stack

- **Language:** Java
- **Build Tool:** Apache Maven
- **Base package:** `com.paynestsystem.app`
- **Java version:** 21

## Project Structure

```text
src/main/java/com/paynestsystem/app/
├── app/
│   └── PayNestApplication.java
├── domain/
│   ├── Customer.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── Product.java
├── payment/
│   ├── BuyNowPayLater.java
│   ├── CardPayment.java
│   ├── EftPayment.java
│   ├── PaymentMethod.java
│   ├── PaymentProcessor.java
│   └── WalletPayment.java
└── service/
    ├── OrderService.java
    ├── OrderSummary.java
    └── OrderSummaryPrinter.java

src/test/java/
├── com/paynestsystem/
│   └── PaymentMethodTest.java
└── com/paynestsystem/app/
    ├── OrderTest.java
    ├── OrderItemTest.java
    ├── OrderServiceTest.java
    └── OrderSummaryPrinterTest.java
```

## Prerequisites

- Java JDK 21
- Apache Maven 3.9+

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Umuzi-skillslab/merchant-order-desk-and-catalogue-engine-WhatAreWe77.git
   ```

2. Compile the project:
   ```bash
   mvn compile
   ```

3. Run tests:
   ```bash
   mvn test
   ```

4. Run the application:
   ```bash
   mvn exec:java
   ```

The application prints an order summary followed by a payment confirmation. Currency values are displayed with two decimal places using South African locale formatting.

## Features

- Customer and product domain models
- Order creation and line-item handling
- Order summary generation through `OrderService`
- Locale-aware South African currency formatting
- Payment method abstraction with card, EFT, wallet, and buy-now-pay-later implementations
- Payment processing that validates positive amounts and reports the payment type and amount
- Wallet payments are declined when the balance is insufficient; successful payments reduce the balance
- Unit tests for order, item, service, summary printing, and payment methods

## Author

Tshepo Sekobile

## License

This project is for educational purposes as part of a capstone.
