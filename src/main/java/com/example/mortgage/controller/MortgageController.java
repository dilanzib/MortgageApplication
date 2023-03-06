package com.example.mortgage.controller;

import com.example.mortgage.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.mortgage.MortgageApplication.calculateMonthlyPayment;
import static com.example.mortgage.MortgageApplication.formatName;

@Controller
public class MortgageController {

    private List<Customer> customers;


    // Initializes the list of customers by parsing the given text file of prospects
    @PostConstruct
    public void init() throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get("prospects/prospects.txt")));
        customers = parseCustomers(fileContent);
    }


    // Handles the GET request to the mortgage endpoint and displays the list of the customers
    @RequestMapping("/mortgage")
    public String welcome(Model model) {
        model.addAttribute("content", customersToString());
        return "mortgage";
    }


    // Handles the POST request to the upload endpoint, uploads a file of prospects and then performs the calculations
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        String result;
        String content;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                content = new String(bytes);
                customers = parseCustomers(content);
                result = "File uploaded successfully and calculations performed";
            } catch (IOException e) {
                result = "File upload failed: " + e.getMessage();
            }
        } else {
            result = "No file selected";
        }
        model.addAttribute("result", result);
        model.addAttribute("content", customersToString());
        return "mortgage";
    }


    // For display purposes it converts the list of customers to a string
    private String customersToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            double monthlyPayment = calculateMonthlyPayment(customer.getTotalLoan(), customer.getInterest(), customer.getYears());
            String formattedName = formatName(customer.getName());
            sb.append(String.format("Prospect %d: %s wants to borrow %.2f € for a period of %d years and pay %.2f € each month\n",
                    i + 1, formattedName, customer.getTotalLoan(), customer.getYears(), monthlyPayment));
        }
        return sb.toString();
    }


    // Parses the content of the text file and creates a list of customer objects
    private List<Customer> parseCustomers(String content) {
        List<Customer> customers = new ArrayList<>();
        String[] lines = content.split("\\r?\\n");
        Pattern pattern = Pattern.compile("^\"?([^\"]*)\"?,(\\d+\\.?\\d*),(\\d+\\.?\\d*),(\\d+)$");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String name = matcher.group(1).replace(",", " ");
                double totalLoan = Double.parseDouble(matcher.group(2));
                double interest = Double.parseDouble(matcher.group(3)) / 100;
                int years = Integer.parseInt(matcher.group(4));
                customers.add(new Customer(name, totalLoan, interest, years));
            }
        }
        return customers;
    }



}
