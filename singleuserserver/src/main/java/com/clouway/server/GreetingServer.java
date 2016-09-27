package com.clouway.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


    private void respond(Socket clientSocket) {
        try {
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            printWriter.println("Hello! " + clock());

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String clock() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}