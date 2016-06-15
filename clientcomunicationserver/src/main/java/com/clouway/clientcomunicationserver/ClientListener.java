package com.clouway.clientcomunicationserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by clouway on 12.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This class is responsible for reading response from client and send it to display.
 */
public class ClientListener implements Runnable {
    private final Socket clientSocket;
    private final Display display;
    private final int numberOfClient;

    public ClientListener(Socket clientSocket, int numberOfClient, Display display) {
        this.clientSocket = clientSocket;
        this.display = display;
        this.numberOfClient=numberOfClient;
    }

    @Override
    public void run() {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (true) {
                String response;
                response = bufferedReader.readLine();
                if (response != null) {
                    display.display("Client "+numberOfClient+ ": " +response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}