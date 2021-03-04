package com;/**
*Created by William - BASIC on 02/03/2021
*UPDATE PROGRAM COMMENTS ABOUR PROGRAM HERE
* */

public class Client extends UserProfile {
   private static int nextUniqueID;
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

   public static int getNextUniqueID() {
      return nextUniqueID;
   }

   public static void setNextUniqueID(int nextUniqueID) {
      Client.nextUniqueID = nextUniqueID;
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
      //todo once dates pulled in, add one day to get the next available slot.
      return "04/04/21";
   }

   @Override
   public void generateOutput() {

   }

   @Override
   public void acceptInput() {

   }

   @Override
   public void saveToCSV() {

   }
   //todo check if all the above are necesary



   public String generateId(){
      int id = nextUniqueID;
      nextUniqueID = nextUniqueID +1;
      return id + "";
   }





}//class
