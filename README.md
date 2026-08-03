# PayNest — Merchant Order Desk & Catalogue Engine

A Java application for managing merchant orders, customers, and product catalogues.

## Overview

PayNest models a simplified order management system, handling customers, products, and orders (including individual order line items) through a service layer.

## Tech Stack

- **Language:** Java
- **Build Tool:** Apache Maven
- **Package:** `com.paynestsystem.app`

## Project Structure

pom.xml
src/main/java/com/paynestsystem/app/
    ├── PayNestApplication.java
    ├── Customer.java
    ├── Product.java
    ├── Order.java
    ├── OrderItem.java
    ├── OrderService.java
    ├── OrderSummary.java
    ├── OrderSummaryPrinter.java

src/test/java/com/paynestsystem/app/
    ├── OrderTest.java
    ├── OrderItemTest.java
    ├── OrderServiceTest.java
    └── OrderSummaryPrinterTest.java

## Prerequisites

- Java JDK 21
- Apache Maven 3.9+

## Getting Started

1. Clone the repository:
   `bash
   git clone https://github.com/Umuzi-skillslab/e12_java-merchant-order-desk-and-catalogue-engine-Merchant-order-desk-and-catalogue-engine.git
   `

2. Compile the project:
   `bash
   mvn compile
   `

3. Run tests:
   `bash
   mvn test
   `

4. Run the application:
   `bash
   mvn exec:java
   `

## Expected Output

```text
PayNest Invoice Summary
Order ID: 1
Customer : Rele-Mbokazi Mkhize
Email  : mbokazimkhize@yahoo.co.za
Items Ordered:
- Samsung Galaxy S26 Ultra x 1 @ R27,999.99 = R27,999.99
- Samsung Galaxy Watch 6 Classic x 3 @ R4,999.99 = R14,999.97
Total Amount: R42,999.96
```

## Features

- Customer management
- Product catalogue
- Order creation and tracking
- Order item / line item handling
- Business logic through `OrderService`
- Immutable order summary with `OrderSummary`
- Console invoice printing separated into `OrderSummaryPrinter`
- Precision money handling with `BigDecimal`
- Locale-aware South African currency formatting
- Unit tests for order, item, service, and printer behavior

## Author

Tshepo Sekobile

## License

This project is for educational purposes as part of a capstone.
