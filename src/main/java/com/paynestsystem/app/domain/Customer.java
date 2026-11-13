package com.paynestsystem.app.domain;

public class Customer {

    private final int id;
    private final String name;
    private final String email;

    public Customer(int id, String name, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("Customer id must be greater than zero.");
        }
        if (name == null) {
            throw new IllegalArgumentException("Customer name cannot be blank.");
        }
        if (email == null) {
            throw new IllegalArgumentException("Customer email cannot be blank.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}