package com.clouway.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
<<<<<<< HEAD
=======

>>>>>>> 279caa4... Main Thread of the server now only accepts Client and makes two threads for reading and writing.
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Server started...");
            GreetingServer greetingServer = new GreetingServer(new ServerSocket(6000));
            Thread thread = new Thread(greetingServer);
            thread.start();
<<<<<<< HEAD
        } catch (IOException e) {
=======
      } catch (IOException e) {
>>>>>>> 279caa4... Main Thread of the server now only accepts Client and makes two threads for reading and writing.
            e.printStackTrace();
        }
    }
}