package com;


import com.google.firebase.database.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/****
 *
 * Created and maintained by William Stewart - B00830662
 *
 * Please base individual assessment for William Stewart
 * primarily on this class, and supplementary class - FirebaseService.
 *
 * This section of the code primarily handles Firebase interaction.
 *
 * Also handles some utility functions necessary for the project,
 * such as ensuring the correct appointment date is set.
 *
 */

public class FireBaseUtilities implements Runnable {

   public static boolean isConfirmed = false;

   /**
    *
    * This method is used to read in data from Firebase.
    * Firebase holds the data in an easily exportable json format.
    * The json file from Firebase is read in as a json object.
    * The object is then put into an array ease of distribution
    * to other parts of the project that
    * need to read in the json file stored in Firebase.
    *
    * The method requires the use of it's own thread (multi threading)
    * to communicate with an external database (Firebase) asynchronously,
    * ensuring that calls to Firebase do not block the rest of the
    * program from running.
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
       * methods contained within for further details on
       * functionality
       */
      ref.addValueEventListener(new ValueEventListener() {

         /**
          * @param dataSnapshot
          * Take a snapshot of the current state of the database,
          * assign this snapshot to an object then pass this
          * to the Object array called 'object'
          *
          * FOR DEBUGGING:
          * UNCOMMENT THE PRINT LINE STATEMENT IF YOU WISH TO SEE WHAT IS CURRENTLY STORED IN FIREBASE
          *
          */
         public void onDataChange(DataSnapshot dataSnapshot) {
            Object document = dataSnapshot.getValue();

//            FOR DEBUGGING: uncomment the following line if you need to see the json being read in from Firebase
//            System.out.println("**********************\n" + "THIS IS WHAT IS CURRENTLY HELD IN FIREBASE: \n" + document + "\n" + "****************************");

            object[0] = document;
         }

         /**
          * @param error
          * if there is an error when taking a snapshot,
          * then send an error message.
          *
          * Calls to the Client and Employee class here for
          * thread consistency (to ensure they communicate on the same thread).
          */
         public void onCancelled(DatabaseError error) {
            System.out.print("Error: " + error.getMessage());
         }
      });

      try {
         Thread.sleep(5000);
         String check = object[0].toString();
         if(check != null){
            System.out.println("****************************************************");
            System.out.println("Firebase link established. \nPROJECT CONTRIBUTORS: \t Darrell Shields \n \t\t\t\t\t\t Christopher Swain \n \t\t\t\t\t\t Peter Spiers \n\t\t\t\t\t\t William Stewart \n");
            System.out.println("****************************************************");
            System.out.println("Please press 1 if you are a customer, or 2 if you are an employee (IF YOU DO NOT, THE PROGRAM WILL NOT PROCEED)");
            Scanner scanner = new Scanner(System.in);
            int choice =0;
            try {
               choice = scanner.nextInt();
            } catch (InputMismatchException nfe) {
               System.out.println("You have entered a non numeric value. System is now shutting down");
               return;
            }
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
    * The data is placed in a Hashmap array (string and the client object for format)
    * to create the json object required by Firebase.
    * When the hashmap is created, a destination path is then set.
    * The HashMap object is then sent to this destination.
    * Hashmap object pushed asynchronously to Firebase to avoid blocking the rest of the program.
    */
   public void sendChanges(String name, String password, String emailAddress, String phone, String date, String car, String problem, String cost, String userName){
      HashMap<String, Client> detailsOfBooking = new HashMap<String, Client>();
      detailsOfBooking.put(userName, new Client(name, password, emailAddress, phone, date, car, problem, cost));
      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference refWrite = database.getReference("Newrec");
      DatabaseReference usersRef = refWrite.child("abc");
      usersRef.push().setValueAsync(detailsOfBooking);
   }


   /***
    *
    * @param data
    * @return
    * @throws ParseException
    * @throws ParseException
    *
    * sets the booking date.
    *
    */
   public String bookingDate(String data) throws ParseException, ParseException {
      String [] array;
      array = data.split("-M");
      String [] dateArray = new String[array.length];
      String [] tempArray = new String[array.length];
      int [] intArray = new int[array.length];
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
      LocalDateTime now = LocalDateTime.now();
      String bookingDate = dtf.format(now);

      //iterate through the date array, identify the date,
      //remove the forward slashes from the dates, add to tempArray.
      for (int i = 1; i < array.length; i++){
         dateArray[i] = (array[i].substring(array[i].indexOf("date=") + 5, array[i].indexOf("date=") + 15));
         tempArray[i] = dateArray[i].replace("/", "");
      }

      //convert strings in tempArray to integers and add these to intArray
      for(int i = 0; i<tempArray.length; i++) {
         try {
            intArray[i] = Integer.parseInt(tempArray[i]);
         }
         catch (NumberFormatException nfe){

         }
      }

      //compare each value in intArray and set bookingDate to the largest.
      for (int i = 0; i < intArray.length; i++) {
         for (int k = i + 1; k < intArray.length; k++){
            if (intArray[i] < intArray[k]){
               bookingDate = dateArray[k];
            }
         }
      }

      //check if a booking date contains more than 5 appointments.
      //If more than 5, set bookingDate to the next day.
      //Else if postage is incurred add two weeks (to ensure appointment is
      //after parts arrive) 15 days if the slot plus two weeks is already full
      //Else if none of the other conditions are met then
      //give client an appointment tomorrow.
      for(int i = 0; i < dateArray.length; i++){
         int count = 0;
         if(Arrays.asList(dateArray[i]).contains(bookingDate)){
            count ++;
            if (count > 4){
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
               Calendar c = Calendar.getInstance();
               c.setTime(sdf.parse(bookingDate));
               c.add(Calendar.DATE, 1);  // number of days to add
               bookingDate = sdf.format(c.getTime());  // set date to following day
               break;
            }
         } else if(isConfirmed) {
            if (count > 4){
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
               Calendar c = Calendar.getInstance();
               c.setTime(sdf.parse(bookingDate));
               c.add(Calendar.DATE, 14);  // number of days to add
               bookingDate = sdf.format(c.getTime());  // set the new date plus 14 days for part to arrive
               break;
            } else {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
               Calendar c = Calendar.getInstance();
               c.setTime(sdf.parse(bookingDate));
               c.add(Calendar.DATE, 15);  // number of days to add
               bookingDate = sdf.format(c.getTime());  // set the new date plus 14 days for part to arrive
               break;
            }
         } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(bookingDate));
            c.add(Calendar.DATE, 1);  // number of days to add
            bookingDate = sdf.format(c.getTime());  // set date to tomorrow
         }
      }
      return bookingDate;
   }

   /**
    * @param check
    *
    * check if a postage charge is incurred (in the the Inventory class)
    *
    */
   public void lookIntoThePostage(boolean check){
      if (check = true){
         isConfirmed = true;
      }
   }

}
