package com.clouway.informationserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class GreetingServer implements Runnable {
    private final ServerSocket serverSocket;
    private final OnlineClients onlineClients;
    private final SocketAgent socketAgent;

    GreetingServer(ServerSocket serverSocket, List onlineClients, List<Socket> clientSockets) {
        this.serverSocket = serverSocket;
        this.onlineClients = new OnlineClients(onlineClients);
        this.socketAgent = new SocketAgent(clientSockets);
    }


    @Override
    public void run() {
        while (true) {
            accept();
            try {
                notifyAllClients();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Accepts client's connection.
     */
    private void accept() {

        try {
            Socket clientSocket = serverSocket.accept();
            onlineClients.putClient();
            respond("",clientSocket);
            socketAgent.put(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Responds to client
     *
     * @param clientSocket used for communication to client.
     */
    private void respond(String message,Socket clientSocket) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(message+ ": " + onlineClients.count());

    }

    private void notifyAllClients() throws IOException {

        for (Socket each : socketAgent.getSockets()) {
            PrintWriter printWriter = new PrintWriter(each.getOutputStream(), true);
            printWriter.println("Number of clients Online: " + onlineClients.count());

        }
    }
}