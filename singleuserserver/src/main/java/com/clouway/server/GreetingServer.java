package com.clouway.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
class GreetingServer implements Runnable {
    private final ServerSocket serverSocket;

    GreetingServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    @Override
    public void run() {
        accept();
    }

    /**
     * Accepts client's connection.
     */
    private void accept() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                respond(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Responds to client
     *
     * @param clientSocket used for communication to client.
     */
    private void respond(Socket clientSocket) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println("Hello! " + formattedDate());

        clientSocket.close();
    }

    /**
     * Formats Date to given scheme.
     *
     * @return Formatted Date.
     */
    private String formattedDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return dateFormat.format(new Date());
    }
}