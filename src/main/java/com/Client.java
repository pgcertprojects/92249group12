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

   public static double addCarTypePremium(String car){
      // Receives a car to search for. Binary searches the first array in a parallel array set.
      // Returns the value stored in the indexed position from the modifier array if found. Other returns 1.0.

      // Declare variables
      int workingNumber, top, bottom, middle;

      // Declare parallel array of car types and their associated price modifier.
      String [] brand = {
              "abarth", "ac", "aixam", "alfa romeo", "alpine", "asia", "aston martin", "audi", "austin", //A (9) = 0-8
              "bentley", "bmw", "bristol", "bugatti", //B (4) = 9-12
              "cadillac", "callaway", "caterham", "chrysler", "citroen", "coleman milne", "corvette", "cupra", //C (8) = 13-20
              "dacia", "daewoo", "daimler", "de tomaso", "dfsk", "dihatsu", "dodge", "ds", //D (8) = 21-28
              "eagle", //E (1) = 29
              "farbio", "fbs", "ferrari", "fiat", "ford", "fso", //F (6) = 30-35
              //G (0)
              "honda", "hummer", "hyundai", //H (3) = 36-38
              "infiniti", "invicta", "isuzu", //I (3) = 39-41
              "jaguar", "jeep", "jenson", //J (3) = 42-44
              "kia", "ktm", //K (2) = 45-46
              "lada", "lamborghini", "lancia", "land rover", "levc", "lexus", "ligier", "lotus", "lti", "lynk", //L (10) = 47-56
              "mahindra", "marcos", "marlin", "maserati", "maybeach", "mazda", "mclaren", "mercedes", "mg", "mia", "microcar", "mini",
              "mitsubishi", "morgan", //M (14) = 57-70
              "nissan", "noble", //N (2) = 71-72
              "opel", //O (1) = 73
              "perodua", "peugeot", "pgo", "polestar", "porsche", "prindiville", "proton", //P (7) = 74-80
              //Q (0)
              "reliant", "renault", "rolls royce", "rover", //R (4) = 81-84
              "saab", "san", "sao", "seat", "skoda", "smart", "ssangyong", "subaru", "suzuki", //S (9) = 85-93
              "talbot", "td cars", "tesla", "toyota", "tvr", //T (5) = 94-98
              //U (0)
              "vauxhall", "volkswagen", "volvo", //V (3) = 99-101
              "westfield", //W (1) = 102
              //X (0)
              "yugo" //Y (1) = 103
              //Z (0)
      };
      double [] costModifier = {
              1.5, 0.9, 0.8, 1.2, 1.0, 0.8, 3.0, 2.0, 1.4, //A (9) = 0-8
              2.5, 2.0, 1.0, 3.5, //B (4) = 9-12
              1.8, 1.2, 1.2, 1.0, 0.9, 1.0, 2.8, 1.1, //C (8) = 13-20
              0.7, 0.6, 0.9, 1.1, 1.0, 1.0, 2.1, 1.0, //D (8) = 21-28
              1.0, //E (1) = 29
              1.1, 1.5, 3.0, 0.8, 1.2, 1.1, //F (6) = 30-35
              //G (0)
              1.5, 2.0, 1.3, //H (3) = 36-38
              1.5, 1.3, 0.9, //I (3) = 39-41
              2.0, 1.4, 1.1, //J (3) = 42-44
              0.8, 1.6, //K (2) = 45-46
              0.7, 3.0, 1.8, 1.7, 1.2, 1.6, 1.1, 2.8, 1.1, 1.3, //L (10) = 47-56
              0.8, 1.0, 1.1, 2.5, 1.0, 1.2, 2.8, 2.0, 0.8, 1.0, 1.1, 1.2, 1.4, 1.1, //M (14) = 57-70
              1.4, 2.5, //N (2) = 71-72
              1.2, //O (1) = 73
              1.3, 0.9, 1.0, 1.1, 2.8, 1.1, 0.7, //P (7) = 74-80
              //Q (0)
              1.5, 0.9, 3.0, 0.8, //R (4) = 81-84
              1.9, 1.0, 1.1, 1.3, 1.4, 1.6, 1.1, 1.8, 1.6, //S (9) = 85-93
              1.2, 1.1, 2.8, 1.6, 2.7, //T (5) = 94-98
              //U (0)
              1.2, 1.6, 1.5, //V (3) = 99-101
              1.2, //W (1) = 102
              //X (0)
              1.0//Y (1) = 103
              //Z (0)
      };

      // Set limits for binary search
      top = brand.length -1;
      bottom = 0;

      // Uses lexicographic binary search to compare the searched string against the value in the brand array.
      // if the values match then workingnumber = 0. If the searched value is greater alphanumerically then
      // workingNumber is greater than 0. If the searched value is less then it is less than zero. e.g...
      // abc < xyz --> -1 or less
      // b = b     --> 0
      // xyz > abc --> 1 or more
      while (bottom <= top) {
         middle = (bottom + top) /2;
         workingNumber = car.toLowerCase().compareTo(brand[middle]);

         if (workingNumber == 0) {
            return costModifier[middle];
         } else if (workingNumber > 0) {
            bottom = middle + 1;
         } else {
            top = middle - 1;
         } //else-if
      }//while

      //If no value is found return a default modifier of 1.0
      return 1.0;
   }//addCarTypePremium


   public static double calculateEstimate(String problem){
      // Receives a problem to search for. Linear searches the first array in a parallel array set, defaults to 150.
      // Then linear searches for the problemType which defaults to repair if not found.
      // Then takes these index positions and returns the cost found in the 2D array.

      //!!NOTE: We could move the costList, problemList and problemType into static variables and call them into this method.
      // This would allow calling of the data into report printouts.

      // Declare and initialise variables
      double cost = 0;
      boolean found = false;
      String [] problemList = { "air conditioning", "air filter", "alloys", "alternator", "belts", "bodywork", "brake discs", "brake fluid", "brake pads", "brakes", //0-9
              "bulbs", "clutch", "coilpack", "coolant", "dent", "flywheel", "fuel pump", "ignition", "ignitioncoils", "inspection", //10-19
              "license plate", "mot", "oil", "oil filter", "paint", "radiator", "scratches", "service", "shock absorbers", "spark plugs", //20-29
              "springs", "starter", "suspension", "timing", "timingbelt", "transmission", "transmission fluid", "tyres", "turbo", "water pump", //30-39
              "wheel bearing", "windscreen"}; //40-41
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
      while ((!found) || (count < problemType.length)) {
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

      return cost; //Note that the car type cost modifier must be applied in a subsequent method.
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