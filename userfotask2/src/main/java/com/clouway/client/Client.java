package com.clouway.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This is a basic input output client for server.
 */
class Client implements Runnable {
    private final Display display;
    private final InetAddress inetAddress;
    private final int port;

    Client(InetAddress inetAddress, int port, Display display) {
        this.inetAddress = inetAddress;
        this.port = port;
        this.display = display;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket(inetAddress,port);
            ServerInput serverInput = new ServerInput(clientSocket, display);
            Thread inputServerMonitorT = new Thread(serverInput);
            inputServerMonitorT.start();
            ClientOutput clientOutput = new ClientOutput(clientSocket);
            Thread clientOutputMonitorT = new Thread(clientOutput);
            clientOutputMonitorT.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}