package com;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
*Created by William - BASIC on 02/03/2021
*UPDATE PROGRAM COMMENTS ABOUR PROGRAM HERE
* */

public class Client extends UserProfile {
//   private static int nextUniqueID;
   private String date;
   private String carType;
   private String problem;
   private String cost;
   private String appointmentDate;


   public Client() {
   }

   public Client(String name, String password, String emailAddress, String phoneNumber, String date, String carType, String problem, String cost, String appointmentDate) {
      super(name, password, emailAddress, phoneNumber);
      this.date = date;
      this.carType = carType;
      this.problem = problem;
      this.cost = cost;
      this.appointmentDate = appointmentDate;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getCarType() {
      return carType;
   }

   public void setCarType(String carType) {
      this.carType = carType;
   }

   public String getProblem() {
      return problem;
   }

   public void setProblem(String problem) {
      this.problem = problem;
   }

   public String getCost() {
      return cost;
   }

   public void setCost(String cost) {
      this.cost = cost;
   }

   @Override
   public void checkCredentials() {

   }

   public double addCarTypePremium(String car){
      double premium =0;
      if(car.equals("Honda")){
         premium = 1.3;
      } else if (car.equals("Toyota")){
         premium = 1.1;
      } else if (car.equals("Ford")){
         premium = 0.9;
      }
      return premium;
   }

   public double calculateEstimate(String problem){
      int cost = 0;
      if(problem.contains("tyres")){
         cost += 40;
      }
      if(problem.contains("wipers")){
         cost += 15;
      }
      return cost;
   }

   @Override
   public String checkAvailableSlot() {
      //todo pull dates already booked.
      //todo once dates pulled in, add one day to get the next available slot, set a limit of the number appointments that can be booked into one day. e.g. 5 appointments a day.
      return "04/04/21";
   }

   public void generateOutput(String username, String password, String isUser) {
      //print booking details
      System.out.println("checkUser() IS WORKING " + username + " " + password);
      System.out.println("***************************************************************");
      System.out.println("DETAILS OF YOUR APPOINTMENT");
      System.out.println("Your appointment is scheduled for: " + isUser.substring(isUser.indexOf("date="), isUser.indexOf(" ")).replace("date=", ""));
      System.out.println("The estimated cost of work to be carried out is: " + isUser.substring(isUser.indexOf("£"), isUser.indexOf(",")));
   }

   @Override
   public void acceptInput(String data) {
      Scanner scanner = new Scanner(System.in);
      Client client = new Client();
      DecimalFormat df = new DecimalFormat("00.00");
      System.out.println("Are you an existing customer? (Y/N)");
      String newCustomerCheck = scanner.nextLine().toLowerCase().trim();
      if (newCustomerCheck.equals("y")){
         System.out.println("please enter your username: ");
         String username = scanner.nextLine();
         System.out.println("please enter your password");
         String password = scanner.nextLine();
         String isUser = client.checkUser(data, username, password);
         if (isUser != null){
            //print booking details
            generateOutput(username, password, isUser);
         }
      }
      if(newCustomerCheck.equals("n")){
         System.out.println("Please enter a new user name of your choice: ");
         String userName = scanner.nextLine();
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
         System.out.println("Your appointment has been scheduled for: " + appointmentDate + "\n" + "The estimated cost will be: " + "£" + df.format(finalCost) + "\n");
         FireBaseUtilities clientDetails = new FireBaseUtilities();
         clientDetails.sendChanges(name, password, emailAddress, phone, dtf.format(now), car, problem, "£" + df.format(finalCost), appointmentDate, userName);
      }
   }



   @Override
   public void saveToCSV() {

   }

   public String checkUser(String data, String user, String password){
      String[] array = data.split("-M");

      for(int i = 0; i< array.length; i++){
         if (array[i].contains(user)) {
            if (array[i].contains(password)) {
               System.out.println(array[i]);
               return array[i];
            }
         }
      }
      return null;
   }


   public String getFBData(String data){
      if (data !=null){
         System.out.println("Employees class getFBData contains: " + data);
         return data;
      } else {
         return "didn't work";
      }
   }

   //todo check if all the above are necesary

}//class
