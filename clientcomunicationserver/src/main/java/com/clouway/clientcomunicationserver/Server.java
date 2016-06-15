package com.clouway.clientcomunicationserver;

import java.io.IOException;
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
public class Server implements Runnable {
    private Socket clientSocket;
    private final ServerSocket serverSocket;
    private final List<Socket> sockets;
    private final Display display;

    Server(int port, List sockets, Display display) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.sockets = sockets;
        this.display = display;
    }

    @Override
    public void run() {

        while (true) {

            try {
                accept();
                ClientListener clientListener = new ClientListener(clientSocket, sockets.size(), display);
                ClientNotifier clientNotifier = new ClientNotifier(clientSocket, sockets);
                Thread streamListener = new Thread(clientListener);
                Thread streamWriter = new Thread(clientNotifier);
                streamListener.start();
                streamWriter.start();
            } catch (IOException ignore) {

            }
        }
    }

    /**
     * Accepts client's connection.
     */
    private void accept() throws IOException {
        this.clientSocket = serverSocket.accept();
        sockets.add(clientSocket);
    }
}