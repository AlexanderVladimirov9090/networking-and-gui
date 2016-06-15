package com.clouway.clientcomunicationserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by clouway on 03.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ClientNotifier implements Runnable {
    private final Socket clientSocket;
    private final List<Socket> sockets;

    public ClientNotifier(Socket clientSocket, List<Socket> sockets) {
        this.clientSocket = clientSocket;
        this.sockets = sockets;
    }


    @Override
    public void run() {

        try {
            respond("Hello Client number: ", clientSocket);
            notifyAllClients();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responds to client by sending what number it is in the system.
     *
     * @param clientSocket used for communication to client.
     */
    private void respond(String message, Socket clientSocket) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(message + sockets.size());
    }

    /**
     * Notify all clients that new one is connected to server.
     *
     * @throws IOException
     */
    private void notifyAllClients() throws IOException {
        for (int i = 0; i < sockets.size() - 1; i++) {
            respond("Number of clients: ", sockets.get(i));
        }
    }
}
