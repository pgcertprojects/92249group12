package com;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FireBaseUtilities db = new FireBaseUtilities();

        db.run();

        Object data = db.getPullDataFromFB();
        System.out.println(data + "TEST");

        Client client = new Client();
        DecimalFormat df = new DecimalFormat("00.00");

        System.out.println("Please enter your name: \n");
        String name = scanner.nextLine();
        System.out.println("Please enter your password: \n");
        String password = scanner.nextLine();
        System.out.println("Please provide an email address: \n");
        String emailAddress = scanner.nextLine();
        System.out.println("Please enter your phone number: \n");
        String phone = scanner.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Please enter the brand of your car");
        String car = scanner.nextLine();
        double carBrandCostAdjustment = client.addCarTypePremium(car);
        System.out.println("Please describe what you need done to your car: \n");
        String problem = scanner.nextLine();
        double cost = client.calculateEstimate(problem);
        double finalCost = cost * carBrandCostAdjustment;
        String appointmentDate = client.checkAvailableSlot();
        System.out.println("Your appointment has been scheduled for: " + appointmentDate + "\n" + " and the estimated cost will be: " + cost + "\n");
        FireBaseUtilities clientDetails = new FireBaseUtilities();
        clientDetails.sendChanges(name, password, emailAddress, phone, dtf.format(now), car, problem, "£" + df.format(finalCost), appointmentDate);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

