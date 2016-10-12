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
    private final SocketAgent socketAgent;
    private final Thread streamMonitor;

    GreetingServer(int port, List sockets, Display display) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.socketAgent = new SocketAgent(sockets);
        this.streamMonitor = new Thread(new StreamMonitor(socketAgent, display));
    }


    @Override
    public void run() {
    streamMonitor.start();
        while (true) {

            try {

                accept();
                notifyAllClients();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Accepts client's connection.
     */
    private void accept() throws IOException {
        Socket clientSocket = serverSocket.accept();
        socketAgent.put(clientSocket);
        respond("You are client number: ", clientSocket);
    }

    /**
     * Responds to client
     *
     * @param clientSocket used for communication to client.
     */
    private void respond(String message, Socket clientSocket) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(message + socketAgent.getSockets().size());
        printWriter.flush();
    }

    private void notifyAllClients() throws IOException {

        for (int i = 0; i < socketAgent.getSockets().size() - 1; i++) {
            respond("Number of clients: ", socketAgent.getSockets().get(i));
        }
    }
}