package com;


import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
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
      }//if
      else {
         return "didn't work";
      }//else
   }//getFBData

   public void chooseMethod(String data){
      boolean runMethod = true;
      int choice;
      String performAnother;

      System.out.print("Which action would you like to perform?\n1.\tGet the total value of current bookings\n" +
              "2.\tPrint out all upcoming bookings\n3.\tCheck a customer's appointment details\n4.\tPrint total postage costs\n\nEnter:\t");

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
               System.out.println("User exists in database: " + checkUser(data) + "\n\n");
               break;
            case 4:
               printPostage(data);
               break;
            default:
               System.out.print("Please enter a valid selection\nEnter:");
               choice = scanner.nextInt();
               System.out.println("");
               break;
         }//switch
         scanner.nextLine();
         System.out.println("Would you like to perform another action? Y/N");
         performAnother = scanner.nextLine();
         performAnother.trim().toLowerCase();
         if (performAnother.contains("y"))
         {
            System.out.print("Which action would you like to perform?\n1.\tGet the total value of current bookings\n" +
                    "2.\tPrint out all upcoming bookings\n3.\tCheck a customer's appointment details\n4.\tPrint total postage costs\n\nEnter:\t");
            try
            {
               choice = scanner.nextInt();
            }//try
            catch (InputMismatchException error)
            {
               System.out.println("A number was not entered, exiting the program.");
               runMethod = false;
            }//catch
         }//if
         else
         {
            System.out.println("Exiting the program.");
            runMethod = false;
         }//else
      }//do
      while (runMethod);


   }//chooseMethod

   private void getValueOfBookings(String data){
      DecimalFormat df = new DecimalFormat("£0.00");
//    System.out.println("IN THE getValueOfBookings(): " + data);
      String[] array;
      array = data.split(" ");
      String numbers = "";

      for(int i = 0; i< array.length; i++){
         if (array[i].contains("£")){
//            System.out.println(array[i]);
            String temp = array[i];
            int index = temp.indexOf("£");
            numbers = numbers + temp.substring(index, temp.indexOf(","));
         }//if
      }//for
      String[] secondNumberArray = numbers.split("£");
      int size = 0;
      double total = 0;
      for (int i=0; i< secondNumberArray.length; i++){
         if(secondNumberArray[i].matches("-?\\d+(\\.\\d+)?")){
            size++;
         }//if
      }//for
      double[] doubleNumberArray = new double[size];
      for (int i = 0; i < size; i++){
         if(secondNumberArray[i].matches("-?\\d+(\\.\\d+)?")){
            doubleNumberArray[i] = Double.parseDouble(secondNumberArray[i]);
            total += doubleNumberArray[i];
         }//if
      }//for
      System.out.println();
      System.out.println("************************************");
      System.out.println("SUMMARY OF BOOKINGS");
      System.out.println();
      System.out.println("Total number of bookings: " + size);
      System.out.println("Average value of bookings: " + df.format(total/size));
      System.out.println("Total value of bookings: " + df.format(total));
      System.out.println("************************************\n\n");
//         System.out.println(Arrays.toString(doubleNumberArray));
      }//getValueOfBookings

   private void printAllAppointments(String data){
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
            }//if (contains name)
         }//if (contains date)
      }//for
      //System.out.println(Arrays.toString(dateArray));
      //System.out.println(Arrays.toString(nameArray));
      //System.out.println(dateArray.length);
   }//printAllAppointments

   private boolean checkUser(String data){
      String[] array;
      array = data.split("-M");
      boolean userExists = false;

      //System.out.println(data);
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the person's name");
      String user = scanner.nextLine();
      System.out.println("Enter the person's email");
      String email = scanner.nextLine();
      email.toLowerCase();

      System.out.println("User's appointment details: ");
      //System.out.println(array[4]);
      for(int i = 0; i< array.length; i++){
         if ((array[i].contains(user)) && (array[i].contains(email))) {
            userExists = true;
            String [] subArray;
            subArray = array[i].split(",");
            for (int count = 0; count< subArray.length; count++) {
               if(subArray[count].contains("date="))
                  System.out.println(subArray[count].substring(subArray[count].indexOf("date=") + 5));
               if (subArray[count].contains("cost="))
                  System.out.println(subArray[count].substring(subArray[count].indexOf("cost=") + 5));
            }//for
         }//if -  if the array contains the username specified
      }//for
      return userExists;
   }//checkUser

   private void printPostage(String data){
      System.out.println("Here is a summary of postage costs:\t");
      Inventory.printPostageCosts();
   }//printPostage



}//class
