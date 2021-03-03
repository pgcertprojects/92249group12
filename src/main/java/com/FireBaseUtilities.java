package com;

import com.google.firebase.database.*;
import java.io.IOException;
import java.util.HashMap;


public class FireBaseUtilities implements Runnable {
   Object pullDataFromFB;
   public void run() {
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
            pullDataFromFB = document;
            System.out.println(document);
         }
         public void onCancelled(DatabaseError error) {
            System.out.print("Error: " + error.getMessage());
         }
      });

      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference refWrite = database.getReference("Newrec");
      DatabaseReference usersRef = refWrite.child("abc");
   }

   public void sendChanges(String name, String password, String emailAddress, String phone, String date, String car, String problem, String cost, String appointmentDate){
      Client client = new Client();
      HashMap<String, Client> detailsOfBooking = new HashMap<String, Client>();
      detailsOfBooking.put(client.generateId(), new Client(name, password, emailAddress, phone, date, car, problem, cost, appointmentDate));
      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference refWrite = database.getReference("Newrec");
      DatabaseReference usersRef = refWrite.child("abc");
      usersRef.push().setValueAsync(detailsOfBooking);
   };

   public Object getPullDataFromFB() {
      return pullDataFromFB;
   }

}
