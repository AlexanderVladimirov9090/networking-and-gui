package com.clouway.informationserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Main {
    public static void main(String[] args) {
        GreetingServer greetingServer = null;
        try {
            System.out.println("Server started...");
            greetingServer = new GreetingServer(6000, new LinkedList());
            Thread thread = new Thread(greetingServer);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
