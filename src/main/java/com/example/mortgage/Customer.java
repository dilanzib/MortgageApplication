package com.example.mortgage;

public class Customer {
    private String name;
    private double totalLoan;
    private double interest;
    private int years;

    public Customer(String name, double totalLoan, double interest, int years) {
        this.name = name;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public double getInterest() {
        return interest;
    }

    public int getYears() {
        return years;
    }
}

