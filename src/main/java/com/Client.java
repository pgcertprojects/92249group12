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
   }//Default Constructor

   public Client(String name, String password, String emailAddress, String phoneNumber, String date, String carType, String problem, String cost, String appointmentDate) {
      super(name, password, emailAddress, phoneNumber);
      this.date = date;
      this.carType = carType;
      this.problem = problem;
      this.cost = cost;
      this.appointmentDate = appointmentDate;
   }//Alternative Constructor

   public String getDate() {
      return date;
   }//getDate

   public void setDate(String date) {
      this.date = date;
   }//setDate

   public String getCarType() {
      return carType;
   }//getCarType

   public void setCarType(String carType) {
      this.carType = carType;
   }//setCarType

   public String getProblem() {
      return problem;
   }//getProblem

   public void setProblem(String problem) {
      this.problem = problem;
   }//setProblem

   public String getCost() {
      return cost;
   }//getCost

   public void setCost(String cost) {
      this.cost = cost;
   }//setCost

   @Override
   public void checkCredentials() {

   }//checkCredentials

   protected double calculateEstimate(String problem, String car){
      // Receives a problem to search for. Linear searches the first array in a parallel array set, defaults to 150.
      // Then linear searches for the problemType which defaults to repair if not found.
      // Then takes these index positions and returns the cost found in the 2D array.

      // Declare and initialise variables
      double cost = 0, carPremium = 1, labourCosts = 0, postageCosts = 0;
      String priority = null;
      boolean found = false;
      String [] problemList = { "air conditioning", "air filter", "alloys", "alternator", "belts", "bodywork", "brake discs", "brake fluid", "brake pads", "brakes", //0-9
            "bulbs", "clutch", "coilpack", "coolant", "dent", "flywheel", "fuel pump", "ignition", "ignitioncoils", "inspection", //10-19
            "license plate", "mot", "oil", "oil filter", "paint", "radiator", "scratches", "service", "shock absorbers", "spark plugs", //20-29
            "springs", "starter", "suspension", "timing", "timingbelt", "transmission", "transmission fluid", "tyres", "turbo", "water pump", //30-39
            "wheel bearing", "windscreen", ""}; //40-41
      int [] clientProblems = {42,42,42,42,42};
      int clientProblemCount = 0;
      String [] problemType = {"inspect", "repair", "replace"}; //default to repair
      int jobType = 0;
      double [] [] costList = {
            //0-9
            {15, 80, 150}, //air conditioning
            {20, 40, 40}, //air filter
            {15, 200, 800}, //alloys
            {25, 80, 200}, //alternator
            {25, 150, 150}, //belts
            {20, 200, 500}, //bodywork
            {15, 150, 150}, //brake discs
            {15, 80, 80}, //brake fluid
            {15, 100, 150}, //brake pads
            {15, 100, 400}, //brakes
            //10-19
            {10, 50, 50}, //bulbs
            {50, 150, 300}, //clutch
            {20, 80, 120}, //coilpack
            {15, 80, 80}, //coolant
            {15, 75, 75}, //dent
            {50, 150, 350}, //flywheel
            {25, 200, 250}, //fuelpump
            {20, 60, 120}, //ignition
            {20, 60, 120}, //ignition coils
            {20, 20, 20}, //inspection
            //20-29
            {5, 25, 50}, //license plate
            {80, 80, 80}, //mot
            {15, 80, 80}, //oil
            {15, 80, 80}, //oil filter
            {20, 75, 200}, //paint
            {25, 100, 200}, //radiator
            {20, 75, 75}, //scratches
            {80, 80, 80}, //service
            {25, 150, 400}, //shock absorbers
            {20, 60, 60}, //spark plugs
            //30-39
            {25, 150, 400}, //springs
            {25, 125, 200}, //starter
            {25, 150, 400}, //suspension
            {25, 60, 120}, //timing
            {20, 60, 120}, //timing belt
            {25, 200, 750}, //transmission
            {25, 85, 85}, //transmission fluid
            {15, 40, 100}, //tyres
            {25, 540, 800}, //turbo
            {25, 75, 200}, //water pump
            //40-41
            {20, 80, 80}, //wheel bearing
            {10, 50, 120}, //windscreen
            //42
            {0,0,0} //Default
      };

      //Finding the problemType, defaults to repair if not found.
      int count = 0;
      while ((!found) && (count < problemType.length)) {
         if (problem.contains(problemType[count])) {
            found = true;
            jobType = count;
         }//if
         count += 1;
      }//while
      if (!found) {
         jobType = 1;
      }//if

      //Finding the clientProblems via linear search and adding the indexes of these to a list of client problems.
      for (int index = 0; index < problemList.length; index++) {
         if (problem.contains(problemList[index])) {
            clientProblemCount += 1;
            clientProblems[clientProblemCount -1] = index;
         }//if
      }//for
      if (clientProblemCount == 0) {
         clientProblemCount = 1;
         clientProblems[0] = 27;
      }//if

      //Calculating total cost from provided information by accessing prices stored in 2D array.
      for (int index = 0; index < clientProblems.length; index++) {
         cost = cost + costList[clientProblems[index]][jobType];
      }//for

      //Calculating car premium and using this to modify labour costs.
      carPremium = Helper.addCarTypePremium(car);
      labourCosts = cost + (cost * carPremium);

      System.out.println("\nHow fast do you require the work to be done? ");
      System.out.println("\t(Please enter standard, fast or emergency. Default value = standard)");
      System.out.println("\t(Note there is some automatic phrase detection for alternative responses.)");
      System.out.print("Response: ");
      priority = Inventory.keyboard.next().toLowerCase();
      //Outputting calculations to user
      System.out.println("\nCalculating estimate for:");
      System.out.println("Car:\t\t\t" + car);
      System.out.println("Problem:\t\t" + problem);
      System.out.println("Job Priority:\t" + priority);
      System.out.println("\n***Labour and Parts Costs***");
      System.out.println("\tDetected Jobs:");
      for (int index = 0; index < clientProblems.length; index++) {
         System.out.println("\t\t" + (index + 1) + ")\t" + problemList[clientProblems[index]]);
      }//for
      System.out.println("\tDetected Job Type:\t" + problemType[jobType]);
      System.out.println("\tLabour costs:\t\t£" + Inventory.currency.format(cost));
      System.out.println("\tCar type modifier:\t" + (carPremium * 100) + "%");
      System.out.println("\nTotal Labour Costs:\t£" + labourCosts);

      //Calculating parts postage cost if required.
      System.out.println("***Parts Postage Costs***");
      postageCosts = Inventory.calculatePostageCost(car, problem, priority);
      if (postageCosts == 0) {
         System.out.println("\tNo postage costs / postage for small consumables included.");
      }//if
      //Calculating, storing and outputting total
      cost = labourCosts + postageCosts;
      System.out.println("Overall total estimate: £" + Inventory.currency.format(cost));

      return cost;
   }//calculateEstimate

   @Override
   public String checkAvailableSlot() {
      //todo pull dates already booked.
      //todo once dates pulled in, add one day to get the next available slot, set a limit of the number appointments that can be booked into one day. e.g. 5 appointments a day.
      return "04/04/21";
   }//checkAvailableSlot

   public void generateOutput(String username, String password, String isUser) {
      //print booking details
      System.out.println("checkUser() IS WORKING " + username + " " + password);
      System.out.println("***************************************************************");
      System.out.println("DETAILS OF YOUR APPOINTMENT");
      System.out.println("Your appointment is scheduled for: " + isUser.substring(isUser.indexOf("date="), isUser.indexOf(" ")).replace("date=", ""));
      System.out.println("The estimated cost of work to be carried out is: " + isUser.substring(isUser.indexOf("£"), isUser.indexOf(",")));
   }//generateOutput

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
         }//if
      }//if
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
         System.out.println("Please enter the brand of your car: \n");
         String car = scanner.nextLine();
         System.out.println("Please describe what you need done to your car ");
         System.out.println("(No more than 5 problems of a single type): \n");
         String problem = scanner.nextLine();
         double finalCost = client.calculateEstimate(problem, car);
         String appointmentDate = client.checkAvailableSlot();
         System.out.println("Your appointment has been scheduled for: " + appointmentDate + "\n" + "The estimated cost will be: " + "£" + df.format(finalCost) + "\n");
         FireBaseUtilities clientDetails = new FireBaseUtilities();
         clientDetails.sendChanges(name, password, emailAddress, phone, dtf.format(now), car, problem, "£" + df.format(finalCost), appointmentDate, userName);
      }//if
   }//if

   @Override
   public void saveToCSV() {
   }//saveToCSV

   public String checkUser(String data, String user, String password){
      String[] array = data.split("-M");

      for(int i = 0; i< array.length; i++){
         if (array[i].contains(user)) {
            if (array[i].contains(password)) {
               System.out.println(array[i]);
               return array[i];
            }//if
         }//if
      }//for
      return null;
   }//checkUser


   public String getFBData(String data){
      if (data !=null){
         System.out.println("Employees class getFBData contains: " + data);
         return data;
      } else {
         return "didn't work";
      }//if-else
   }//getFBData

   //todo check if all the above are necessary

}//class
