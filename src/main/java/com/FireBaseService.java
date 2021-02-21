package com;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class FireBaseService {


   FirebaseDatabase db;

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

//   URL url = new URL("http://www.example.com/resource");
//   HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//   httpCon.setDoOutput(true);
//   httpCon.setRequestMethod("PUT");
//   OutputStreamWriter out = new OutputStreamWriter(
//           httpCon.getOutputStream());
//   out.write("Resource content");
//   out.close();
//   httpCon.getInputStream();
//
//
//   //To perform an HTTP DELETE:
//   URL url = new URL("http://www.example.com/resource");
//   HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//   httpCon.setDoOutput(true);
//   httpCon.setRequestProperty(
//        "Content-Type", "application/x-www-form-urlencoded" );
//   httpCon.setRequestMethod("DELETE");
//   httpCon.connect();

}
