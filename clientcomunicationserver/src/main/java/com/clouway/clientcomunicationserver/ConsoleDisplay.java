package com.clouway.clientcomunicationserver;

/**
 * Created by clouway on 28.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This is concrete implementation for Display interface.
 */

class ConsoleDisplay implements Display {
    @Override
    public void display(String message) {
        System.out.println(message);
    }
}