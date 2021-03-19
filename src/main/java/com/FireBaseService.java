package com;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/***
 *
 * Created by William Stewart
 *
 * Primary purpose of this class is to point
 * Firebase utilities at the correct endpoint
 * for our persistent object (json) database.
 *
 * Also, validates credentials for access to
 * Firebase
 *
 * PLEASE CONTACT: William Stewart
 * for decommissioning of this link.
 *
 *
 */
public class FireBaseService {
   FirebaseDatabase db;

   /**
    * @throws IOException
    *
    * read in the json key file for database authentication
    *
    * provide target url and initialise when FireBaseService() is called
    *
    */
   public FireBaseService() throws IOException {
      File file = new File(
//              getClass().getClassLoader().getResource("key.json").getFile()
              "key.json"
      );

      FileInputStream fis = new FileInputStream(file);

      FirebaseOptions options = new FirebaseOptions.Builder()
              .setCredentials(GoogleCredentials.fromStream(fis))
              .setDatabaseUrl("https://javagroup12-b5d63-default-rtdb.firebaseio.com/")
              .build();

      FirebaseApp.initializeApp(options);

      db = FirebaseDatabase.getInstance();
   }

   public FirebaseDatabase getDb() {
      return db;
   }
}
