package com.clouway.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
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
            connect();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Connects to given server.
     */
    private void connect() throws IOException {
        Socket clientSocket = new Socket(inetAddress, port);
        ServerInputMonitor serverInputMonitor = new ServerInputMonitor(clientSocket, display);
        Thread inputServerMonitorT = new Thread(serverInputMonitor);
        inputServerMonitorT.start();
        ClientOutputMonitor clientOutputMonitor = new ClientOutputMonitor(clientSocket);
        Thread clientOutputMonitorT = new Thread(clientOutputMonitor);
        clientOutputMonitorT.start();
        while (!(clientSocket.getOutputStream() ==null)){

        }
        throw new NoSocketException("we");
    }
}