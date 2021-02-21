package com;

import com.google.firebase.database.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShowDbChanges implements Runnable {
   public void run() {
      FireBaseService fbs = null;
      try {
         fbs = new FireBaseService();
      } catch (IOException e) {
         e.printStackTrace();
      }

      DatabaseReference ref = fbs.getDb()
              .getReference("/");
      ref.setValueAsync("helo");
      ref.addValueEventListener(new ValueEventListener() {

         public void onDataChange(DataSnapshot dataSnapshot) {
            Object document = dataSnapshot.getValue();
            System.out.println(document);
         }


         public void onCancelled(DatabaseError error) {
            System.out.print("Error: " + error.getMessage());
         }
      });

//      DatabaseReference usersRef = ref.child("users");
//      Map<String, User> users = new HashMap<>();
//      users.put("alanisawesome", new User("June 23, 1912", "Alan Turing"));
//      users.put("gracehop", new User("December 9, 1906", "Grace Hopper"));
//
//      usersRef.setValueAsync(users);
//
//        usersRef.child("alanisawesome").setValueAsync(new User("June 23, 1912", "Alan Turing"));
//        usersRef.child("gracehop").setValueAsync(new User("December 9, 1906", "Grace Hopper"));
//
//        DatabaseReference hopperRef = usersRef.child("gracehop");
//        Map<String, Object> hopperUpdates = new HashMap<>();
//        hopperUpdates.put("nickname", "Amazing Grace");
//
//        hopperRef.updateChildrenAsync(hopperUpdates);
//
//        Map<String, Object> userUpdates = new HashMap<>();
//        userUpdates.put("alanisawesome/nickname", "Alan The Machine");
//        userUpdates.put("gracehop/nickname", "Amazing Grace");
//
//        usersRef.updateChildrenAsync(userUpdates);
//
//
//        usersRef.setValueAsync("Hello,World!");
//
//        usersRef.child("someChild").child("name").setValueAsync("hello");
//
//        usersRef.child("users").setValueAsync("hello");
   }

}
