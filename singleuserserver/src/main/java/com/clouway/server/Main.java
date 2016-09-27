package com.clouway.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Main {
    public static void main(String[] args) {
        try {
            GreetingServer greetingServer = new GreetingServer(new ServerSocket(6000));
            Thread thread = new Thread(greetingServer);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}