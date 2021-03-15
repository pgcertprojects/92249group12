package com;

import java.text.DecimalFormat;

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
      System.out.println("************************************");
//         System.out.println(Arrays.toString(doubleNumberArray));
      }

      //might not need this checkUser for employees (just clients)
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

}//class
