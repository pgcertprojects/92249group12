package com;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *Created by William - BASIC on 02/03/2021
 *UPDATE PROGRAM COMMENTS ABOUR PROGRAM HERE
 * */

public class Client extends UserProfile {
   private String date;
   private String carType;
   private String problem;
   private String cost;


   public Client() {
   }//Default Constructor

   public Client(String name, String password, String emailAddress, String phoneNumber, String date, String carType, String problem, String cost) {
      super(name, password, emailAddress, phoneNumber);
      this.date = date;
      this.carType = carType;
      this.problem = problem;
      this.cost = cost;
   }//Alternative Constructor

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


   protected double calculateEstimate(String problem, String car){
      // Receives a problem to search for. Linear searches the first array in a parallel array set, defaults to 150.
      // Then linear searches for the problemType which defaults to repair if not found.
      // Then takes these index positions and returns the cost found in the 2D array.
      // Declare and initialise variables
      double cost = 0, carPremium = 1, labourCosts = 0, postageCosts = 0, discount = 0;
      String priority = null, responseDiscount;
      boolean found = false;
      String [] problemList = { "air con", "air filter", "alloys", "alternator", "belts", "bodywork", "brake discs", "brake fluid", "brake pads", "brakes", //0-9
            "bulbs", "clutch", "coilpack", "coolant", "dent", "flywheel", "fuel pump", "ignition", "ignitioncoils", "inspect", //10-19
            "license plate", "mot", "oil", "oil filter", "paint", "radiator", "scratches", "service", "shock absorbers", "spark plugs", //20-29
            "springs", "starter", "suspension", "timing", "timingbelt", "transmission", "transmission fluid", "tyres", "turbo", "water pump", //30-39
            "wheel bearing", "windscreen", ""}; //40-41
      int [] clientProblems = {19,42,42,42,42};
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
      for (int index = 0; (index < problemList.length) && (clientProblemCount < 5); index++) {
         if (problem.contains(problemList[index])) {
            clientProblems[clientProblemCount] = index;
            clientProblemCount += 1;
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
      labourCosts = cost * carPremium;

      //Prompting user for response and accepting their input
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
      System.out.println("\n***Parts Postage Costs***");
      postageCosts = Inventory.calculatePostageCost(car, problem, priority);
      if (postageCosts == 0) {
         System.out.println("\tNo postage costs / postage for small consumables included in labour costs");
      }//if
      //Calculating, storing and outputting total
      cost = labourCosts + postageCosts;

      //Asking user for discount code
      System.out.println("Do you have a discount code (please enter y or n): ");
      responseDiscount = Inventory.keyboard.next().toLowerCase();
      if (responseDiscount.equals("y")) {
         discount = Inventory.discountCode();
         cost = cost - discount;
      } else if(responseDiscount.equals("n")) {
         System.out.println("No discount code selected. ");
      } else {
         System.out.println("Invalid response. Continuing without discount");
      }//else

      System.out.println("\n***Overall Cost Estimate***");
      System.out.println("Estimate: £" + Inventory.currency.format(cost));
      System.out.println("(Note that this is just an estimate and that actual prices incurred may vary.)\n");

      return cost;
   }//calculateEstimate

   //print booking details
   public void generateOutput(String username, String password, String isUser) {
      String temp = "";
      System.out.println("*************************************************************** \n");
      System.out.println("DETAILS OF YOUR APPOINTMENT");
      System.out.println("Your appointment is scheduled for: " + isUser.substring(isUser.indexOf("date="), isUser.indexOf(" ")).replace("date=", ""));
      String[] arr = isUser.split(" ");
      for (int i = 0; i < arr.length; i++){
         if (arr[i].contains("£")){
            temp = arr[i];
            temp = temp.substring(temp.indexOf("£"), temp.indexOf(","));
         }
      }
      System.out.println("The estimate cost for work to be carried out is " + temp + "\n");
      System.out.println("***************************************************************");
   }

   @Override
   public void acceptInput(String data) throws ParseException
   {
      String forTheBookingDateMethod = data;
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
         validateName(name);
         System.out.println("Please enter your password: \n");
         String password = scanner.nextLine();
         System.out.println("Please provide an email address: \n");
         String emailAddress = scanner.nextLine();
         validateEmail(emailAddress);
         System.out.println("Please enter your phone number: \n");
         String phone = scanner.nextLine();
         validatePhone(phone);
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
         LocalDateTime now = LocalDateTime.now();
         System.out.println("Please enter the brand of your car: \n");
         String car = scanner.nextLine();
         System.out.println("Please describe what you need done to your car ");
         System.out.println("\t(Pleas describe the type of problem using either \"inspect\", \"repair\" or \"replace\" keywords");
         System.out.println("\t(Only 1 type of job of no more than 5 problems/jobs per appointment please.\nResponse: \n");
         String problem = scanner.nextLine();
         double finalCost = client.calculateEstimate(problem, car);
//         String appointmentDate = client.checkAvailableSlot();
         FireBaseUtilities clientDetails = new FireBaseUtilities();
         String theAppointmentDate = clientDetails.bookingDate(forTheBookingDateMethod);
         System.out.println("Your appointment has been scheduled for: " + theAppointmentDate + "\n" + "The estimated cost will be: " + "£" + df.format(finalCost) + "\n");
         clientDetails.sendChanges(name, password, emailAddress, phone, theAppointmentDate + " 09:00:00", car, problem, "£" + df.format(finalCost), userName);
      }//if
   }//if


   public String checkUser(String data, String user, String password){
      String[] array = data.split("-M");

      for(int i = 0; i< array.length; i++){
         if (array[i].contains(user+"=")) {
            if (array[i].contains("password=" + password + ",")) {
               System.out.println(array[i]);
               return array[i];
            }//if
         }//if
      }//for
      System.out.println("your username or password is incorrect. Exiting system");
      return null;
   }//checkUser

   public static String validateName(String name){
      int count =3;
      Scanner keyboard = new Scanner(System.in);
      for (int i = 0; i < 3; i++){
         if (isword(name)){
            System.out.println("That name is valid, thank you "+name);
            break;
         } else if(count !=0){
            count--;
            System.out.println("Invalid name, please try again, you have " + count + " chances left to enter a valid name.");
            name = keyboard.nextLine();
         } else {
            return null;
         }
      }
      return name;
   }


   //method to take new customer email address and validate input
   //includes regex to confirm that email is in correct configuration
   public static String validateEmail (String emailAddress) {
        int chance = 3;
        Scanner scanner = new Scanner(System.in);
        for (int count = 0; count < 3; count++) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@+(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (pattern.matcher(emailAddress).matches()) {
            System.out.println("That is a valid email address, "+emailAddress+" thank you");
            break;
            } else if(chance !=0) {
               chance--;
               System.out.println("Invalid email address, please try again, you have " + chance + " more chance(s) to enter a valid name.");
            } else {
                  System.out.println("Sorry you had 3 attempts, system shutdown");
                  return null;
            }
        }
        return emailAddress;
   }

   //regex to check that only letters are entered
   public static boolean isword(String in) {
      return Pattern.matches("([A-Z]{1})([a-z]+)(\\s)([A-Z]{1})([a-z]+){1}", in);
   }


        //method to take in new customer phone number
   //includes regex to validate that input is only digits
   public static String validatePhone (String phone) {
        int chance=3;
        Scanner scanner = new Scanner(System.in);
        for (int count=0;count<3; count++) {
            if(phoneno(phone)){
               System.out.println("That is a valid phone number, "+phone+" thank you\n");
               return phone;
            } else {
               chance--;
               System.out.println("Invalid telephone number, please try again, you have " + chance + " more chance(s) to enter a valid number");
               if (chance == 0) {
               System.out.println("Sorry you had 3 attempts, system shutdown");
               return null;
               }
            }
        }
        return phone;
   }

   public static boolean phoneno(String num){
        return Pattern.matches("[0-9]+", num);
   }


}//class

