package com.clouway.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
class Client implements Runnable {
    private final InetAddress inetAddress;
    private final int port;

    Client(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }

    @Override
    public void run() {
        connect();
    }

    /**
     * Connects to given server.
     */
    private void connect() {
        try (Socket clientSocket = new Socket(inetAddress, port)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            listen(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Listens to server response and prints in to the console.
     * @param bufferedReader
     * @throws IOException
     */
    private void listen(BufferedReader bufferedReader) throws IOException {
        String response;

        while ((response = bufferedReader.readLine()) != null) {
            System.out.println("Server Response: " + response);
        }
    }
}