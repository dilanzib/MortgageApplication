package com.example.mortgage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class MortgageApplication {

    public static final String PROSPECTS_FILE = "prospects/prospects.txt";

    public static void main(String[] args) {
        SpringApplication.run(MortgageApplication.class, args);
    }

    public static List<Customer> readProspectsFile(String prospectsFile) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(prospectsFile))) {
            String line;
            Pattern pattern = Pattern.compile("^\"?([^\"]*)\"?,(\\d+\\.?\\d*),(\\d+\\.?\\d*),(\\d+)$");
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String name = matcher.group(1).replace(",", " ");
                    double totalLoan = Double.parseDouble(matcher.group(2));
                    double interest = Double.parseDouble(matcher.group(3)) / 100;
                    int years = Integer.parseInt(matcher.group(4));
                    customers.add(new Customer(name, totalLoan, interest, years));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }


    public static double calculateMonthlyPayment(double totalLoan, double interest, int years) {
        double monthlyInterest = interest / 12;
        int months = years * 12;
        double numerator = totalLoan * monthlyInterest;
        double denominator = 1;
        for (int i = 0; i < months; i++) {
            denominator *= (1 + monthlyInterest);
        }
        return numerator / (1 - (1 / denominator));
    }


    public static String formatName(String name) {
        return name.replace(",", " ");
    }

}

