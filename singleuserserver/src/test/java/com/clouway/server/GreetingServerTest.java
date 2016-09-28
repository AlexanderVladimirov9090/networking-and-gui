package com.clouway.server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 26.09.16.
 */
public class GreetingServerTest {

    @Test
    public void happyPath() throws IOException, InterruptedException {
        String expected = generateExpectedResponse();
        GreetingServer greetingServer = new GreetingServer(new ServerSocket(6060));
        Thread serverTread = new Thread(greetingServer);
        serverTread.start();

        String actual = getServerResponse();

        assertThat(actual, is(equalTo(expected)));
    }

    /**
     * Generates expected response from server.
     *
     * @return excepted response from server.
     */
    private String generateExpectedResponse() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return "nullHello! " + dateFormat.format(new Date());
    }

    /**
     * Open client socket reads response from server and returns it.
     *
     * @return response.
     * @throws IOException
     */
    private String getServerResponse() throws IOException {
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6060);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String response;

        while ((response = bufferedReader.readLine()) != null) {
            return response;
        }
        return null;
    }
}