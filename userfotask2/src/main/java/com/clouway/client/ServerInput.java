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
 *         This class is used to listen and send to display server respons.
 */
class ServerInput implements Runnable {
    private final Display display;
    private final Socket clientSocket;

    ServerInput(Socket clientSocket, Display display) {
        this.display = display;
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (true) {
                String response;
                response = bufferedReader.readLine();
                if (!(response == null)) {
                    display.display(response);
                }else {
                    throw new NoSocketException("");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(clientSocket);
        }

    }

    /**
     * Closes client socket.
     *
     * @param clientSocket
     */
    private void close(Socket clientSocket) {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}