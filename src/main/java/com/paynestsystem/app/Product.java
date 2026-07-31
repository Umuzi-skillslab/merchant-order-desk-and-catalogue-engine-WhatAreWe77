package com.paynestsystem.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;

    public Product(int id, String name, BigDecimal price) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product id must be greater than zero.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be blank.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price cannot be null or negative.");
        }
        this.id = id;
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Product(int id, String name, double price) {
        this(id, name, BigDecimal.valueOf(price));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

