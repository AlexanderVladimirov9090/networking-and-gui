package com.clouway.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by clouway on 07.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ServerInputMonitor implements Runnable {
    private final Display display;
    private final Socket clientSocket;

    public ServerInputMonitor(Socket clientSocket, Display display) {
        this.display = display;
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String response;
        while ((response = bufferedReader.readLine()) != null) {
            display.display(response);
        }
    }
}
