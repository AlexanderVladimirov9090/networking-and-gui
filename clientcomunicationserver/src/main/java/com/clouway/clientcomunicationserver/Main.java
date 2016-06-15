package com.clouway.clientcomunicationserver;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Server started...");
            Server server = new Server(7000, new LinkedList(), new ConsoleDisplay());
            Thread greetingServeT = new Thread(server);
            greetingServeT.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}