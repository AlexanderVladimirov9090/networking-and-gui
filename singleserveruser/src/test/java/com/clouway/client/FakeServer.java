package com.clouway.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
class FakeServer implements Runnable {
    private final int port;
    private final Date date;

    FakeServer(int port, Date date){
        this.port=port;
        this.date = date;
    }

    @Override
    public void run() {
        accept();
    }

    /**
     * Accepts client`s connection.
     */
    private void accept() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            respond(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responds to client
     *
     * @param clientSocket used for communication to client.
     */
    private void respond(Socket clientSocket) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(date.toString());
        clientSocket.close();
    }
}
