package com;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by William - BASIC on 06/03/2021
 * UPDATE PROGRAM COMMENTS ABOUR PROGRAM HERE
 */
public class Employee {

   public String getFBData(String data){
      if (data !=null){
         System.out.println("Employees class getFBData contains: " + data);
         return data;
      } else {
         return "didn't work";
      }
   }

   public void chooseMethod(String data){
      boolean runMethod = true;
      int choice;
      char performAnother;

      System.out.print("Which action would you like to perform?\n1.\tGet the total value of current bookings\n" +
              "2.\tPrint out all upcoming bookings\n3.\tCheck a customer's appointment details\n\nEnter:\t");

      Scanner scanner = new Scanner(System.in);
      choice = scanner.nextInt();

      do
      {
         switch (choice)
         {
            case 1:
               getValueOfBookings(data);
               break;
            case 2:
               printAllAppointments(data);
               break;
            case 3:
               System.out.println(checkUser(data));
               break;
            default:
               System.out.println("Please enter a valid selection");
               choice = scanner.nextInt();
               break;
         }//switch
         scanner.nextLine();
         System.out.println("Would you like to perform another action? Y/N");
         performAnother = scanner.nextLine().charAt(0);
         if ((performAnother == 'y') || (performAnother == 'Y'))
         {
            System.out.print("Which action would you like to perform?\n1.\tGet the total value of current bookings\n" +
                    "2.\tPrint out all upcoming bookings\n3.\tCheck a customer's appointment details\n\nEnter:\t");
            choice = scanner.nextInt();
         }
         else
            runMethod = false;
      }//do
      while(runMethod);
   }//chooseMethod

   public void getValueOfBookings(String data){
      DecimalFormat df = new DecimalFormat("£0.00");
//      System.out.println("IN THE getValueOfBookings(): " + data);
      String[] array;
      array = data.split(" ");
      String numbers = "";

      for(int i = 0; i< array.length; i++){
         if (array[i].contains("£")){
//            System.out.println(array[i]);
            String temp = array[i];
            int index = temp.indexOf("£");
            numbers = numbers + temp.substring(index, temp.indexOf(","));
         }
      }
      String[] secondNumberArray = numbers.split("£");
      int size = 0;
      double total = 0;
      for (int i=0; i< secondNumberArray.length; i++){
         if(secondNumberArray[i].matches("-?\\d+(\\.\\d+)?")){
            size++;
         }
      }
      double[] doubleNumberArray = new double[size];
      for (int i = 0; i < size; i++){
         if(secondNumberArray[i].matches("-?\\d+(\\.\\d+)?")){
            doubleNumberArray[i] = Double.parseDouble(secondNumberArray[i]);
            total += doubleNumberArray[i];
         }
      }
      System.out.println();
      System.out.println("************************************");
      System.out.println("SUMMARY OF BOOKINGS");
      System.out.println();
      System.out.println("Total number of bookings: " + size);
      System.out.println("Average value of bookings: " + df.format(total/size));
      System.out.println("Total value of bookings: " + df.format(total));
      System.out.println("************************************\n\n");
//         System.out.println(Arrays.toString(doubleNumberArray));
      }

   public void printAllAppointments(String data){
      String [] array;
      array = data.split("-M");

      System.out.println("Printing a list of all the current appointments");

      String [] dateArray = new String[array.length];
      String [] nameArray = new String[array.length];
      System.out.println("Date\t\t\tName");

      //parallel array to print out the dates of current appointments and the name of who has them

      for (int count = 1; count < array.length; count++){
         if (array[count].contains("date=")){
            if (array[count].contains("name=")) {
               //System.out.println(array[count]);
               dateArray[count] = (array[count].substring(array[count].indexOf("date=") + 5, array[count].indexOf("date=") + 15));
               nameArray[count] = (array[count].substring(array[count].indexOf("name=") + 5, array[count].indexOf("}")));
               if (!(dateArray[count].equals(dateArray[count-1])))
                  System.out.printf("%-15s %-15s %n" , dateArray[count], nameArray[count]);
               else
                  System.out.printf("\t\t\t\t" + nameArray[count] + "\n");
            }
         }
      }//for
      //System.out.println(Arrays.toString(dateArray));
      //System.out.println(Arrays.toString(nameArray));
      //System.out.println(dateArray.length);
   }//printAllAppointments

   public boolean checkUser(String data){
      String[] array;
      array = data.split("[{]");
      boolean userExists = false;

      //System.out.println(data);
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the person's username");
      String user = scanner.nextLine();

      //System.out.println(array[4]);
      for(int i = 0; i< array.length; i++){
         if (array[i].contains(user)) {
            userExists = true;
         }
      }
      return userExists;
   }

   public void randomMethod(String data){
      System.out.println("This is the random method" + data);
      Inventory.printPostageCosts();
   }



}//class
