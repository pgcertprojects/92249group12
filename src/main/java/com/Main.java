package com;


public class Main {

    public static void main(String[] args) {
        //IF YOU WISH TO VIEW THE CONTENT CURRENTLY HELD ON FIREBASE PLEASE UNCOMMENT THE
        //PRINT LINE STATEMENT IN THE onDataChange() METHOD HOUSED IN THE FireBaseUtilities CLASS

        //SEE USER GUIDE FOR EXAMPLE QUERIES, AND ANY PASSWORDS YOU MAY REQUIRE FOR PROGRAM
        //REVIEW

        //IF YOU EXPERIENCE CONNECTION ISSUES IT MAY BE DUE TO YOUR ENVIRONMENT
        //OR INTERNET CONNECTION. IF YOU EXPERIENCE ANY ISSUES PLEASE FIRST TRY
        //ADJUSTING THE 'Thread.sleep' METHOD BELOW TO 10000 OR HIGHER.

        //NOTE: Please email William Stewart for Firebase link decommissioning
        // (stewart-w1@ulster.ac.uk or stewart-w24@hotmail.co.uk)

        System.out.println("PLEASE WAIT WHILE WE WARM UP (TAKES A FEW SECONDS)");

        FireBaseUtilities db = new FireBaseUtilities();
        db.run();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

