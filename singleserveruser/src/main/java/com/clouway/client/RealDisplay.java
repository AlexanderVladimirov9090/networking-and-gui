package com.clouway.client;

/**
 * Created by clouway on 28.09.16.
 */
public class RealDisplay implements Display {
    @Override
    public void display(String message) {
        System.out.println("Server Response: "+ message);
    }
}
