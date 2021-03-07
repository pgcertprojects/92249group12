package com;


import com.google.firebase.database.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class FireBaseUtilities implements Runnable {
   public void run() {
      final Object[] object = new Object[1];
      FireBaseService fbs = null;
      try {
         fbs = new FireBaseService();
      } catch (IOException e) {
         e.printStackTrace();
      }

      DatabaseReference ref = fbs.getDb()
              .getReference("/");

      ref.addValueEventListener(new ValueEventListener() {
         public void onDataChange(DataSnapshot dataSnapshot) {
            Object document = dataSnapshot.getValue();
//            System.out.println(document);
            object[0] = document;
         }
         public void onCancelled(DatabaseError error) {
            System.out.print("Error: " + error.getMessage());
         }
      });
      try {
         Thread.sleep(5000);
         String check = object[0].toString();
         if(check != null){
            System.out.println("Firebase link established");
            System.out.println("please press 1 if you are a customer, or 2 if you are an employee \n");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if(choice == 2){
               System.out.println("please enter the employee code");
               int code = scanner.nextInt();
               if (code == 3505){
                  Employee employee = new Employee();
                  employee.getFBData(check);
                  employee.getValueOfBookings(check);
               } else {
                  System.out.println("incorrect employee code");
                  return;
               }
            } else {
               Client client = new Client();
               client.acceptInput(object[0].toString());
            }
         }
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public void sendChanges(String name, String password, String emailAddress, String phone, String date, String car, String problem, String cost, String appointmentDate, String userName){
      HashMap<String, Client> detailsOfBooking = new HashMap<String, Client>();
      detailsOfBooking.put(userName, new Client(name, password, emailAddress, phone, date, car, problem, cost, appointmentDate));
      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference refWrite = database.getReference("Newrec");
      DatabaseReference usersRef = refWrite.child("abc");
      usersRef.push().setValueAsync(detailsOfBooking);
   }

}
