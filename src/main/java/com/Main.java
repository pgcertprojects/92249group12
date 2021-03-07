package com;


public class Main {

    public static void main(String[] args) {

        FireBaseUtilities db = new FireBaseUtilities();
        db.run();





        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

