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
    private final Display display;

    GreetingServer(int port, List<Socket> clientSockets, RealDisplay display) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.socketAgent = new SocketAgent(clientSockets);
        this.display = display;
    }


    @Override
    public void run() {
        synchronized (socketAgent){
            Thread streamThread = new Thread(new StreamMonitor(socketAgent, display));
            streamThread.start();
            while (true) {
                accept();
                try {
                    notifyAllClients();
                streamThread.interrupt();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * Accepts client's connection.
     */
    private void accept() {

        try {
            Socket clientSocket = serverSocket.accept();
            respond("You are client number: ", clientSocket);
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
    private void respond(String message, Socket clientSocket) throws IOException {

        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(message + socketAgent.getSockets().size());

    }

    private void notifyAllClients() throws IOException {
        for (int i = 0; i < socketAgent.getSockets().size() - 1; i++) {
            respond("Number of clients online: ", socketAgent.getSockets().get(i));
        }
    }


}