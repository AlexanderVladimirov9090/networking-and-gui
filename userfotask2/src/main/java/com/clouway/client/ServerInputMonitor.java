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
 * This class is used to listen and send to display server respons.
 */
class ServerInputMonitor implements Runnable {
    private final Display display;
    private final Socket clientSocket;

    ServerInputMonitor(Socket clientSocket, Display display) {
        this.display = display;
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() throws NoSocketException {
        while(true) {
            try {
                listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Listens to server response and send it to display.
     * @throws IOException
     */
    private synchronized void listen() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String response;
        response = bufferedReader.readLine();

        if (response == null) {
            throw new NoSocketException("Server down");
        }
        display.display(response);
    }
    }