package com;

import java.util.Scanner;

/**
 * Created by Chris on 10/03/2021
 * Class which allows creation of parts objects which have their own unique serial numbers. Methods to process included.
 **/
public class Inventory {


   //Declaring static variables, constants and objects
      static final int MINNUMOFSUPPLIERS = 2;
      static final double MARKUP = 1.5;
      static int nextPartNumber = 1000;
      static int productCount = 0;
      static Scanner keyboard = new Scanner(System.in);


   //Declaring instance variables
      private String partName;
      private int serialNumber, quantityInStock, quantityOrdered;
      private String [] suppliers = new String [MINNUMOFSUPPLIERS];
      private double wholesaleCostPerUnit;


   //Declaring Constructors - Note that both constructors automatically generate serial numbers and update the product count.
      protected Inventory(String partName) {
         generateSerialNumber();
         this.partName = partName;
      }//Default Constructor - partName required at very minimum.

      protected Inventory(String partName, String [] suppliers, int quantityInStock, int quantityOrdered, double wholesaleCostPerUnit) {
         generateSerialNumber();
         this.partName = partName;
         this.suppliers = suppliers;
         this.quantityInStock = quantityInStock;
         this.quantityOrdered = quantityOrdered;
         this.wholesaleCostPerUnit = wholesaleCostPerUnit;
      }//Alternative Constructor - All details.


   //Getter and Setter Methods.
      protected void setPartName(String partName) {
         this.partName = partName;
      }//setPartName

      protected String getPartName() {
         return partName;
      }//getPartName

      protected void setQuantityInStock(int quantityInStock) {
         this.quantityInStock = quantityInStock;
      }//setPartName

      protected int getQuantityInStock() {
         return quantityInStock;
      }//getQuantityInStock

      protected void setQuantityOrdered(int quantityOrdered) {
         this.quantityOrdered = quantityOrdered;
      }//setPartName

      protected int getQuantityOrdered() {
         return quantityOrdered;
      }//getQuantityOrdered

      protected int getSerialNumber() {
         return serialNumber;
      }//getSerialNumber

      protected void setSupplier(int indexPosition, String supplierName) {
         //Declaring new empty array.
         String [] newSuppliers = new String [2];

         //Logic
         if (indexPosition == 0) {
            newSuppliers[0] = supplierName;
         } else if (indexPosition == 1) {
            newSuppliers[1] = supplierName;
         } else {
            System.out.println("Error: Invalid index position. ");
         }//else

         //Pointing memory point to newly created supplier list.
         suppliers = newSuppliers;
      }//getSupplier

      protected String [] getSuppliers() {
         return suppliers;
      }//getSuppliers

      protected void setCostPerUnit(double wholesaleCostPerUnit) {
         this.wholesaleCostPerUnit = wholesaleCostPerUnit;
      }//costPerUnit


   //Instanced processing methods.
      private void generateSerialNumber() {
         //Automatically runs every time a constructor is used. No need to be manually called.
         serialNumber = nextPartNumber;
         nextPartNumber += 1;
         productCount +=1;
      }//generateSerialNumber

      protected double calculateRetailCostPerUnit() {
         //Calculates the cost to the customer per part including admin costs for the company.
         return wholesaleCostPerUnit * MARKUP;
      }//calculateRetailCostPerUnit

      protected void changeSuppliers() {
         //Declare method variables
         String supplier0, supplier1;

         //Prompt and read in users new suppliers after outputting the old suppliers.
         System.out.println("Two suppliers are required for each part. ");
         System.out.println("Supplier on file for this part are: ");
         for (int index = 0; index < MINNUMOFSUPPLIERS; index++) {
            System.out.print(getSuppliers()[index]);
         }//for
         System.out.print("\nWhat would you like the first supplier to be: ");
         supplier0 = keyboard.next();
         System.out.print("\nWhat would you like the second supplier to be: ");
         supplier1 = keyboard.next();

         setSupplier(0, supplier0);
         setSupplier(1, supplier1);
      }//setSupplier

      protected double calculateTotalPartsCost() {
         //CURRENTLY WORKING ON.
         return 0;
      }//calculateTotalPartsCost

      protected double calculateTotalOrderedPartsCost() {
         //CURRENTLY WORKING ON.
         return 0;
      }//calculateTotalOrderedPartsCost


   //Static methods
      protected static void printSupplierList() {
         System.out.println("Part No.\t\tSupplier 1\t\tSupplier 2");
         for (int index = 0; index < productCount; index++) {
            //System.out.println(serialNumber + "\t\t" + suppliers[0] + "\t\t" + suppliers[1]);
         }//for
      }//printSupplierList

      protected static void printPartsList() {
         System.out.println("Part No.\t\tSupplier 1\t\tSupplier 2");
         for (int index = 0; index < productCount; index++) {
            //System.out.println(serialNumber + "\t\t" + suppliers[0] + "\t\t" + suppliers[1]);
         }//for
      }//printPartsList

   //toString method.
      public String toString() {
         String availability;
         if (quantityInStock >= 1) {
            availability = "in stock. ";
         } else availability = "not in stock. ";
         return ("\nPart " + serialNumber + " is " + availability);
      }//toString

}//class
