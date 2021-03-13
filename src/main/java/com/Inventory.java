package com;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by Chris on 10/03/2021
 * Class which searches for car origin country, parts in client problem and then generates an estimate of parts + postage.
 * This class performs static operations on dynamic elements which are passed in from the client class.
 * Some of the operations and data management in this class have been made more complex to demonstrate a range of techniques as requested.
 **/
public class Inventory {

   //Declaring non-persisting instance variables
   private int partNameIndex;
   private int partSizeIndex;

   //Declaring constructor method
   private Inventory() {
   }//Default constructor

   private Inventory(int partNameIndex) {
      this.partNameIndex = partNameIndex;
   }//Alternative constructor

   //Declaring objects for reading user input and formatting currency/percentages.
   static protected Scanner keyboard = new Scanner(System.in);
   static protected DecimalFormat currency = new DecimalFormat("0.00");
   static protected DecimalFormat percent = new DecimalFormat("00.0");

   //Declaring and initialising constants
   static protected final double MARKUP = 1.15;

   //Declaring and initialising 1 dimensional arrays
   private static String [] postagePriority = {"economy", "priority", "express"};
   private static String [] postageSize = {"None", "letter", "parcel ", "pallet"};
   private static String [] supplier = {
         "American Auto Inc          ", "Car Parts Importers NI Ltd ", "Europa DasAuto gmbh        ",
         "GB Mechanical Suppliers Ltd", "Irish Motor Supplies Ltd   ", "Worldwide Automotive Ltd   "
   };//supplier

   //Declaring and initialising a one dimensional parallel array set (Parallel array 1: nationalities and nationalitySupplier)
   private static String [] nationalities = {
         "american", "austrian", "british", "chinese", "czech", "default", "european", "french", "german", "indian", //0-9
         "italian", "japanese", "korean", "polish", "russian", "malaysian", "south african", "spanish", "swedish" //10-18
   };//nationalities
   private static int [] nationalitySupplier = {0, 2, 3, 1, 2, 2, 2, 2, 2, 4, 2, 1, 1, 2, 2, 4, 4, 2, 2};

   //Declaring and initialising a one dimensional parallel array set (Parallel array 2: carParts & carPartsSize)
   private static String [] carParts = {
         "air con", "air filter", "alloys", "alternator", "belts", "bodywork", "brake discs", "brake fluid", "brake pads", "brakes", //0-9
         "bulbs", "clutch", "coilpack", "coolant", "default", "flywheel", "fuel pump", "ignition", "ignitioncoils", "license plate", //10-19
         "oil", "oil filter", "paint", "radiator", "shock absorbers", "spark plugs", "springs", "starter", "suspension", "timing", //20-29
         "timingbelt", "transmission", "transmission fluid", "tyres", "turbo", "water pump", "wheel bearing", "windscreen", "none"//30-38
   };//carParts
   private static int [] carPartsSize = {
         2, 2, 3, 2, 2, 3, 2, 2, 2, 2, //0-9
         1, 2, 2, 2, 2, 2, 2, 2, 2, 2, //10-19
         2, 2, 2, 3, 3, 2, 3, 2, 3, 1, //20-29
         2, 3, 2, 3, 2, 2, 2, 3, 0//30-38
   };//carPartsSize

   //Declaring and initialising a one dimensional parallel array set (Parallel array 3: carBrand & carNationality)
   private static String [] carBrand = {
         //Parallel array containing the brands of cars for detection.
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
   };//carBrand

   private static String [] carNationality = {
         //Parallel array of carBrand containing nationalities/country of parts of the indexed positions.
         "italian", "british", "french", "italiano", "french", "korean", "british", "german", "british", //A (9) = 0-8
         "british", "german", "british", "french", //B (4) = 9-12
         "american", "american", "british", "american", "french", "british", "american", "spanish", //C (8) = 13-20
         "romanian", "korean", "british", "italian", "chinese", "japanese", "american", "french", //D (8) = 21-28
         "british", //E (1) = 29
         "british", "european", "italian", "italian", "american", "polish", //F (6) = 30-35
         //G (0)
         "japanese", "american", "korean", //H (3) = 36-38
         "japanese", "british", "japanese", //I (3) = 39-41
         "british", "american", "british", //J (3) = 42-44
         "korean", "austrian", //K (2) = 45-46
         "russian", "italian", "italian", "british", "british", "japanese", "arabian", "british", "british", "swedish", //L (10) = 47-56
         "indian", "british", "british", "italian", "german", "japanese", "british", "german", "british", "french", "british", "british",
         "japanese", "british", //M (14) = 57-70
         "japanese", "british", //N (2) = 71-72
         "german", //O (1) = 73
         "malaysian", "french", "french", "british", "german", "british", "malaysian", //P (7) = 74-80
         //Q (0)
         "british", "french", "british", "british", //R (4) = 81-84
         "swedish", "indian", "south african", "spanish", "czech", "german", "korean", "japanese", "japanese", //S (9) = 85-93
         "british", "british", "american", "japanese", "british", //T (5) = 94-98
         //U (0)
         "german", "german", "swedish", //V (3) = 99-101
         "british", //W (1) = 102
         //X (0)
         "italian" //Y (1) = 103
         //Z (0)
   };//carNationality

   //Declare and initialise 3D array
   private static double [] [] [] supplierPostage = {
         //3D array containing postage prices for 6 suppliers, each has 3 priorities of mail sending and 3 postage sizes.
         {
               {0.00, 0.00, 0.00, 0.00, 0.00, 0.00},//economy
               {0.00, 0.00, 0.00, 0.00, 0.00, 0.00},//priority
               {0.00, 0.00, 0.00, 0.00, 0.00, 0.00},//express
         },//none
         {
               {5.00, 1.00, 3.00, 1.00, 1.50, 5.00},//economy
               {10.00, 5.00, 6.00, 2.00, 3.69, 10.00},//priority
               {40.00, 10.00, 12.50, 5.00, 6.50, 60.00},//express
         },//letter
         {
               {20.00, 2.00, 10.50, 5.00, 5.00, 25.00},//economy
               {60.00, 7.50, 15.60, 7.00, 7.50, 65.00},//priority
               {100.00, 15.00, 20.00, 10.00, 11.00, 110.00},//express
         },//parcel
         {
               {60.00, 20.00, 50.00, 40.00, 30.00, 80.00},//economy
               {120.00, 40.00, 80.00, 65.00, 45.00, 175.00},//priority
               {200.00, 50.00, 150.00, 100.00, 70.00, 250.00},//express
         }//pallet
   };//supplierPostage

   //Getter and setter method for non-persisting instanced variables accessed exclusively from within this class
   private void setPartNameIndex(int partNameIndex) {
      this.partNameIndex = partNameIndex;
   }//setPartNameIndex

   private int getPartNameIndex() {
      return partNameIndex;
   }//getPartNameIndex

   private void setPartSizeIndex(int partSizeIndex) {
      this.partSizeIndex = partSizeIndex;
   }//setPartSizeIndex

   private int getPartSizeIndex() {
      return partSizeIndex;
   }//getPartSizeIndex

   //Get methods for accessing class static variables outside of class.
   protected static String [] getBrand() {
      return carBrand;
   }//getBrand

   protected static String getIndexedBrand(int index) {
      return carBrand[index];
   }//getIndexedBrand

   //Get methods which are called exclusively in this class.
   private static String getIndexedPostageSize(int index) {
      return postageSize[index];
   }//getIndexedPostageSize

   private static String getIndexedSupplier(int index) {
      return supplier[index];
   }//getIndexedSupplier

   private static String getIndexedPostagePriority(int index) {
      return postagePriority[index];
   }//getIndexedSupplier

   //Print methods
   protected static void printPostageCosts() {
      //Prints values from the 3D array for a breakdown of postage costs from supplier by size and priority.
      //Header and ia are set to 1 to avoid printing the "none" values which are all 0.
      System.out.print("\n\t\t\t\t\t\t");
      for (int header = 1; header < postageSize.length; header++) {
         System.out.print("\t\t" + postageSize[header]);
      }//for
      for (int ip = 0; ip < postagePriority.length; ip++) {
         System.out.print("\nPriority: " + postagePriority[ip]);
         for (int is = 0; is < supplier.length; is++) {
            System.out.print("\n\t" + supplier[is]);
            for (int ia = 1; ia < postageSize.length; ia++) { //skip "none"
               System.out.print("\t£" + currency.format(supplierPostage[ia][ip][is]));
            }//for
         }//for
      }//for
      System.out.println("\n"); //2 Blank lines after report.
   }//printPostageCosts

   //Processing methods
   private static boolean postageRequired(String problem) {
      //Method which checks whether job type requires postage.

      //Declaring and initialising variables
      boolean found = false;
      String [] problemType = {"inspect", "fit", "repair", "replace", "fix"};
      int jobType = 0;

      //Finding the problemType, defaults to repair if not found.
      int count = 0;
      while ((!found) && (count < (problemType.length))) {
         if (problem.contains(problemType[count])) {
            found = true;
            jobType = count;
         }//if
         count += 1;
      }//while

      //If nothing is found the jobtype defaults to repair which will require parts.
      if (!found) {
         jobType = 2;
      }//if

      //Performing check to see if job requires postage.
      if (jobType > 1) {
         return true;
      } else {
         return false;
      }//if-else
   }//postageRequired

   private static int [] generatePartsRequired(String problem) {
      //Finds index positions of parts via linear search and feeds these into an array which is returned.

      //Declaring and initialising variables
      String [] problemList = carParts;
      int [] clientParts = {38,38,38,38,38};
      int clientPartsCount = 0;

      //Finding the clientProblems via linear search and adding the indexes of these to a list of client problems.
      for (int index = 0; (index < problemList.length) || (index < clientParts.length); index++) {
         if (problem.contains(problemList[index])) {
            clientParts[clientPartsCount] = index;
            clientPartsCount += 1;
         }//if
      }//for
      if (clientPartsCount == 0) {
         clientParts[0] = 14; //Part is set to a default part if it is not found in the partslist.
      }//if
      return clientParts;
   }//generatePartsRequired

   private static int generateSupplier(String carNationality) {
      //Method which returns the index position of the supplier from a given nationality.

      //Declaring and initialising variables
      boolean found = false;
      int supplierNum = 2;

      //Linear searching and assigning variable
      for (int index = 0; index < nationalities.length; index++) {
         if (carNationality.equals(nationalities[index])) {
            supplierNum = nationalitySupplier[index];
         }//if
      }//for

      //Returning the index position of the supplier in the supplier array.
      return supplierNum;
   }//generateSupplier

   private static int generateMaxSize(int[] clientParts) {
      //Method which returns the maximum size package size as an integer from the inputted parameter array.
      int maxSize = 0;

      for (int index = 0; index < clientParts.length; index++) {
         if (carPartsSize[clientParts[index]] > maxSize) {
            maxSize = carPartsSize[clientParts[index]];
         }//if
      }//for
      return maxSize;
   }//generateMaxSize

   private static String generateCarNationality(String car) {
      // Receives a car to search for. Binary searches the first array in a parallel array set.
      // Returns the value stored in the indexed position from the modifier array if found. Other returns 1.0.

      // Declare variables
      int workingNumber, top, bottom, middle;

      // Declare parallel array of car types and their associated nationality.
      String [] brand = Inventory.carBrand;
      String [] nationality = Inventory.carNationality;

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
            return nationality[middle].intern();
         } else if (workingNumber > 0) {
            bottom = middle + 1;
         } else {
            top = middle - 1;
         } //else-if
      }//while

      //If no value is found return a default modifier of 1.0
      return "default";
   }//generateCarNationality

   private static int generatePostagePriority(String priority) {
      // Detects common phrases that are used to describe priority and returns a standardised response.
      // Declare and initialise method variables
      int newPriority = 0, test1, test2, test3; //Defaults to lowest priority
      String [] low = {"basic", "economy", "slow", "low", "standard"};
      String [] medium = {"fast", "urgent", "priority"};
      String [] high = {"fastest", "nextday", "next day", "express", "emergency"};

      //Calling method from helper class to calculate values and assigning these values
      test1 = Helper.searchPostagePhrases(priority, low, 1);
      test2 = Helper.searchPostagePhrases(priority, medium, 2);
      test3 = Helper.searchPostagePhrases(priority, high, 3);

      //Nested if statements to determine the priority
      if(test3 > newPriority) {
         newPriority = test3;
      } if(test2 > newPriority) {
         newPriority = test2;
      } else if (test1 > newPriority) {
         newPriority = test1;
      }//else-if

      return newPriority;
   }//generatePostagePriority

   private static double generateMarkup(double cost) {
      //Calculates and returns the cost of markup for the job to the client including admin costs for the company.
      return (cost * MARKUP) - cost;
   }//generateMarkup

   private static String capitalise(String str) {
      //Method which capitalises the first letter of a string.
      if((str == null) || (str.isEmpty())) {
         return str;
      }//if
      return str.substring(0, 1).toUpperCase() + str.substring(1);
   }//capitalise

   protected static double calculatePostageCost(String car, String problem, String priority) {
      // Method which is called statically from the calculateEstimate() method in client.
      // Most of the data is arranged in arrays. However some data is to be held in inventory objects for the purposes of demonstration of objects use.
      // Calculates postage costs for new clients in order to generate their total cost which is held as an instance variable.
      // Objects and variables inside this class are not intended to be accessed from the rest of the package unless they are declared protected.
      // Objects declared in this class are not intended to persist beyond run-time and are not intended to be stored in the firebase database.

      //Declare method variables
      double partsCost = 0, finalPartsCost = 0, markup = 0, cost = 0;
      String carNationality;
      final int MAX = 5; //Setting number of objects to be created and size of parts arrays.
      int [] partsRequired = new int [MAX];
      int supplier, maxSize, postagePriority;
      boolean required;

      //Begin calculations
      partsRequired = generatePartsRequired(problem);
      required = postageRequired(problem);

      //If job is inspection (or does not require parts) then postage calculation does not run.
      if (required) {
         //Create 5 new non-persisting inventory objects through an array to hold minor details of parts for the output below.
         Inventory [] tempParts = new Inventory[MAX];
         for (int index = 0; index < MAX; index++) {
            tempParts[index] = new Inventory(partsRequired[index]);
         }//for

         //Using a for loop to set the partsize of the objects from the array.
         for (int index = 0; index < MAX; index++) {
            tempParts[index].setPartSizeIndex(carPartsSize[partsRequired[index]]);
         }//for

         postagePriority = generatePostagePriority(priority);
         maxSize = generateMaxSize(partsRequired);
         carNationality = generateCarNationality(car);
         supplier = generateSupplier(carNationality);
         partsCost = supplierPostage[maxSize][postagePriority][supplier];

         markup = generateMarkup(partsCost);
         cost = markup + partsCost;

         //Outputting calculations and detected postage requirements using a mixture of data from that stored in arrays and in objects
         System.out.println("\n***Postage Costs***");
         System.out.println("\tCar Parts Origin:\t\t" + capitalise(carNationality));
         System.out.println("\tPostage Priority:\t\t" + getIndexedPostagePriority(postagePriority));
         System.out.println("\tParts List:");
         for (int index = 0; index < MAX; index++) {
            System.out.println("\t\t" + (index + 1) + ")\t" + capitalise(carParts[tempParts[index].getPartNameIndex()]) + "\t\t" + capitalise(postageSize[tempParts[index].getPartSizeIndex()]));
         }//for
         System.out.println("\tLargest part size:\t\t" + capitalise(getIndexedPostageSize(maxSize)));
         System.out.println("\tSupplier:\t\t\t\t" + getIndexedSupplier(supplier));
         System.out.println("\tPostage Cost:\t\t\t£" + currency.format(partsCost));
         System.out.println("\tMechanic admin markup:\t" + percent.format((MARKUP - 1) * 100) + "%");
         System.out.println("\tMechanic admin charge:\t£" + currency.format(cost));

         //Swapping the values of cost and finalPartsCost without using a third variable
         finalPartsCost = finalPartsCost + cost;   //x = x + y
         cost = finalPartsCost - cost;             //y = x - y
         finalPartsCost = finalPartsCost - cost;   //x = x - y

         //Outputting final postage calculation
         System.out.println("\nTotal postage cost:\t£" + currency.format(finalPartsCost));

         //Final return statement
         return finalPartsCost;
      } else {
         return finalPartsCost = 0;
      }//if-else
   }//calculatePostageCost
}//class