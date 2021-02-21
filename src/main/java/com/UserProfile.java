package com;

/**
 * Created by William - BASIC on 18/02/2021
 * UPDATE PROGRAM COMMENTS ABOUR PROGRAM HERE
 */
abstract public class UserProfile {
   private String name;
   private String id;
   private String password;

   public UserProfile(String name, String id, String password) {
      this.name = name;
      this.id = id;
      this.password = password;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public abstract void checkCredentials();

   public abstract void calculateEstimate();

   public abstract void checkAvailableSlot();

   public abstract void generateOutput();

   public abstract void acceptInput();

   public abstract void saveToCSV();


}//class
