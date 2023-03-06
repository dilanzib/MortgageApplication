package com.example.mortgage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MortgageApplicationTests {

    @Test
    void testReadProspectsFile() {
        List<Customer> customers = MortgageApplication.readProspectsFile("prospects/prospects.txt");
        Assertions.assertNotNull(customers);
        Assertions.assertEquals(4, customers.size());

        Customer firstCustomer = customers.get(0);
        Assertions.assertEquals("Juha", firstCustomer.getName());
        Assertions.assertEquals(1000.0, firstCustomer.getTotalLoan());
        Assertions.assertEquals(0.05, firstCustomer.getInterest());
        Assertions.assertEquals(2, firstCustomer.getYears());

        Customer secondCustomer = customers.get(1);
        Assertions.assertEquals("Karvinen", secondCustomer.getName());
        Assertions.assertEquals(4356, secondCustomer.getTotalLoan());
        Assertions.assertEquals(0.0127, secondCustomer.getInterest());
        Assertions.assertEquals(6, secondCustomer.getYears());

        Customer thirdCustomer = customers.get(2);
        Assertions.assertEquals("Claes Månsson", thirdCustomer.getName());
        Assertions.assertEquals(1300.55, thirdCustomer.getTotalLoan());
        Assertions.assertEquals(0.0867, thirdCustomer.getInterest());
        Assertions.assertEquals(2, thirdCustomer.getYears());

        Customer fourthCustomer = customers.get(3);
        Assertions.assertEquals("Clarencé Andersson", fourthCustomer.getName());
        Assertions.assertEquals(2000.0, fourthCustomer.getTotalLoan());
        Assertions.assertEquals(0.06, fourthCustomer.getInterest());
        Assertions.assertEquals(4, fourthCustomer.getYears());
    }

    @Test
    void testCalculateMonthlyPayment() {
        double expected = 1062.61;
        double actual = MortgageApplication.calculateMonthlyPayment(100000, 0.0504, 10);
        Assertions.assertEquals(expected, actual, 0.01);
    }


    @Test
    void testFormatName() {
        String input = "Clarencé,Andersson";
        String expected = "Clarencé Andersson";
        String actual = MortgageApplication.formatName(input);
        Assertions.assertEquals(expected, actual);
    }

}

