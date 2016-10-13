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
 *         This class accepts connection from client and greets it by response.
 */
public class ServerReceptionist implements Runnable {
    private final ServerSocket serverSocket;
    private final SocketAgent socketAgent;
    private final Display display;

    ServerReceptionist(int port, List sockets, Display display) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.socketAgent = new SocketAgent(sockets);
        this.display = display;
    }

    @Override
    public void run() {

        while (true) {

            try {

                accept();
                notifyAllClients();
                ClientInputMonitor clientInputMonitor = new ClientInputMonitor(socketAgent, display);
                Thread streamMT = new Thread(clientInputMonitor);
                streamMT.start();

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
     * Responds to client by sending what number it is in the system.
     *
     * @param clientSocket used for communication to client.
     */
    private void respond(String message, Socket clientSocket) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(message + socketAgent.getSockets().size());
    }

    /**
     * Notify all clients that new one is connected to server.
     *
     * @throws IOException
     */
    private void notifyAllClients() throws IOException {

        for (int i = 0; i < socketAgent.getSockets().size(); i++) {
            respond("Number of clients: ", socketAgent.getSockets().get(i));
        }
    }
}