package com.clouway.informationserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by clouway on 30.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
class FakeClient {
    private String response;
    private final InetAddress inetAddress;
    private final int port;
    private final String messageToServer;

    FakeClient(InetAddress inetAddress, int port, String meseageToServer) {
        this.inetAddress = inetAddress;
        this.port = port;
        this.messageToServer = meseageToServer;
    }

    /**
     * Connects to given server.
     */
    void connect() {
        try (Socket clientSocket = new Socket(inetAddress, port)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            listen(bufferedReader, clientSocket);
            respond(clientSocket);
        } catch (IOException ignore) {

        }
    }

    private void disconnect(Socket clientSocket) throws IOException {
        clientSocket.close();

    }

    /**
     * Listens to server response and prints in to the console.
     *
     * @param bufferedReader
     * @throws IOException
     */
    private void listen(BufferedReader bufferedReader, Socket socket) throws IOException {
        String response;

        while ((response = bufferedReader.readLine()) != null) {
            this.response += response;
            disconnect(socket);
        }
    }

    private void respond(Socket socket) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println(messageToServer);
    }

    String getResponse() {
        return response;
    }
}