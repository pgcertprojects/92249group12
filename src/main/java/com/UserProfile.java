package com;

/**
 * Created by William - BASIC on 18/02/2021
 * UPDATE PROGRAM COMMENTS ABOUR PROGRAM HERE
 */
abstract public class UserProfile {
   private String name;
   private String password;
   private String emailAddress;
   private String phoneNumber;

   public UserProfile(String name, String password, String emailAddress, String phoneNumber) {
      this.name = name;
      this.password = password;
      this.emailAddress = emailAddress;
      this.phoneNumber = phoneNumber;
   }

   public UserProfile() {
   }

   public abstract String checkAvailableSlot();

   public abstract void acceptInput(String data);

}//class
