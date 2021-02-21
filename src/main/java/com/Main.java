package com;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        //test
        //test
        //test

        //the following is test of the firebase link
        Thread t = new Thread(new ShowDbChanges());

        t.run();
        try
        {
            Thread.sleep(100000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

}

