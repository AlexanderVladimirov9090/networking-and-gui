package com.clouway.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by clouway on 07.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 * This class is used to respond to server.
 */
class ClientOutputMonitor implements Runnable {
    private final Socket clientSocket;

    ClientOutputMonitor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                respond();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Respond to server by console.
     * @throws IOException
     */
    private void respond() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String response = bufferedReader.readLine();
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(response);
    }
}