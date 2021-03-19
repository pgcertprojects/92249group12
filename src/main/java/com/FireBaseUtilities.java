package com;


import com.google.firebase.database.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

/****
 *
 * Created and maintained by William Stewart - B00830662
 *
 * This section of the code primarily handles Firebase interaction.
 * Also handles some utility functions necessary for the project.
 *
 */

public class FireBaseUtilities implements Runnable {

   /**
    *
    * This method is used to read in data from Firebase.
    * Firebase holds the data in an easily exportable json format.
    * The json from Firebase is read in as a json object.
    * The object array for distribution
    * to other parts of the project that
    * need to read in the json file stored in Firebase.
    *
    * The method requires the use of it's own thread (multi threading)
    * to communicate with an external database (Firebase) asynchronously.
    *
    */
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

      /**
       * Anonymous method to check for any changes in Firebase,
       * containing further methods. See descriptions of
       * methods contained within for durther details on
       * functionality
       */
      ref.addValueEventListener(new ValueEventListener() {

         /**
          * @param dataSnapshot
          * Take a snapshot of the current state of the database
          * assign this snapshot to an object then pass this
          * to the Object array called 'object'
          */
         public void onDataChange(DataSnapshot dataSnapshot) {
            Object document = dataSnapshot.getValue();
//            System.out.println(document);
            object[0] = document;
         }

         /**
          * @param error
          * if there is an error when taking a snapshot,
          * then send an error message
          */
         public void onCancelled(DatabaseError error) {
            System.out.print("Error: " + error.getMessage());
         }
      });

      try {
         Thread.sleep(5000);
         String check = object[0].toString();
         if(check != null){
            System.out.println("Firebase link established");
            System.out.println("Please press 1 if you are a customer, or 2 if you are an employee");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if(choice == 2){
               System.out.println("Please enter the employee code");
               int code = scanner.nextInt();
               while ((code != 3505) && (code != -1)) {
                  System.out.println("Incorrect employee code. Try again or press -1 to exit");
                  code = scanner.nextInt();
               }
               if (code == 3505){
                  Employee employee = new Employee();
                  employee.getFBData(check);
                  employee.chooseMethod(check);
               }
               else {
                  System.out.println("Leaving program");
               }
            } else {
               Client client = new Client();
               client.acceptInput(check);
            }
         }
      } catch (InterruptedException | ParseException e) {
         e.printStackTrace();
      }
   }

   /***
    *
    * @param name
    * @param password
    * @param emailAddress
    * @param phone
    * @param date
    * @param car
    * @param problem
    * @param cost
    * @param userName
    *
    * sendChanges reads in all the data that we want to send to Firebase
    * The data is placed in a Hashmap array to create the json object
    * required by Firebase.
    * When the hashmap is created, a destination path is then set.
    * The HashMap object is then sent to this destination.
    */
   public void sendChanges(String name, String password, String emailAddress, String phone, String date, String car, String problem, String cost, String userName){
      HashMap<String, Client> detailsOfBooking = new HashMap<String, Client>();
      detailsOfBooking.put(userName, new Client(name, password, emailAddress, phone, date, car, problem, cost));
      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference refWrite = database.getReference("Newrec");
      DatabaseReference usersRef = refWrite.child("abc");
      usersRef.push().setValueAsync(detailsOfBooking);
   }


   public String bookingDate(String data) throws ParseException, ParseException {
      String [] array;
      array = data.split("-M");
      String [] dateArray = new String[array.length];
      String [] tempArray = new String[array.length];
      int [] intArray = new int[array.length];
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
      LocalDateTime now = LocalDateTime.now();
      String bookingDate = dtf.format(now);

      for (int i = 1; i < array.length; i++){
         dateArray[i] = (array[i].substring(array[i].indexOf("date=") + 5, array[i].indexOf("date=") + 15));
         tempArray[i] = dateArray[i].replace("/", "");
      }

      for(int i = 0; i<tempArray.length; i++) {
         try {
            intArray[i] = Integer.parseInt(tempArray[i]);
         }
         catch (NumberFormatException nfe){

         }
      }

      for (int i = 0; i < intArray.length; i++) {
         for (int k = i + 1; k < intArray.length; k++){
            if (intArray[i] < intArray[k]){
               bookingDate = dateArray[k];
            }
         }
      }

      for(int i = 0; i < dateArray.length; i++){
         int count = 0;
         if(Arrays.asList(dateArray[i]).contains(bookingDate)){
            count ++;
            if (count > 4){
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
               Calendar c = Calendar.getInstance();
               c.setTime(sdf.parse(bookingDate));
               c.add(Calendar.DATE, 1);  // number of days to add
               bookingDate = sdf.format(c.getTime());  // dt is now the new date
               break;
            }
         } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(bookingDate));
            c.add(Calendar.DATE, 1);  // number of days to add
            bookingDate = sdf.format(c.getTime());  // dt is now the new date
            break;
         }
      }
      return bookingDate;
   }


}
