package com.clouway.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Main {
    public static void main(String[] args) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 7000, new ConsoleDisplay());
            Thread clientThread = new Thread(client);
            clientThread.start();
        } catch (UnknownHostException e) {
            throw new NoSocketException("Server Down");
        }
    }
}