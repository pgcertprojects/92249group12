package com;

/**
 *
 * Created and maintained by Chris Swain
 *
 */

public class Helper {

   protected static int searchPostagePhrases(String priority, String [] dictionary, int affinity) {
      // Method which receives 3 parameters and conducts and linear search for parameter priority on the values in the parameter array.
      // Returns parameter affinity if found.

      //Declaring and initialising variable
      int detectedPriority = 0;

      //Linear search
      for (int index = 0; index < dictionary.length; index++) {
         if (dictionary[index].equals(priority)) {
            detectedPriority = affinity;
         }//if
      }//for
      return detectedPriority;
   }//searchPostagePhrases

   protected static double addCarTypePremium(String car){
      // Receives a car to search for. Binary searches the first array in a parallel array set.
      // Returns the value stored in the indexed position from the modifier array if found. Other returns 1.0.

      // Declare variables
      int workingNumber, top, bottom, middle;

      // Declare parallel array of car types and their associated price modifier.
      String [] brand = Inventory.getBrand();
      double [] labourCostModifier = {
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
            return labourCostModifier[middle];
         } else if (workingNumber > 0) {
            bottom = middle + 1;
         } else {
            top = middle - 1;
         } //else-if
      }//while

      //If no value is found return a default modifier of 1.0
      return 1.0;
   }//addCarTypePremium
}//class
