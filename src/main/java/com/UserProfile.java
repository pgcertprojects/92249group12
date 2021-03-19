package com;

import java.text.ParseException;

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

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getEmailAddress() {
      return emailAddress;
   }

   public void setEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public UserProfile() {
   }

   public abstract String checkAvailableSlot();

   public abstract void acceptInput(String data) throws ParseException;

}//class
