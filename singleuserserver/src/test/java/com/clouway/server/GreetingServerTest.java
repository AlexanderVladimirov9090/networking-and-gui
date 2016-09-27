package com.clouway.server;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 26.09.16.
 */
public class GreetingServerTest {

    @Test
    public void happyPath() throws IOException, InterruptedException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String expected = "nullHello! " + dateFormat.format(new Date());
        GreetingServer greetingServer = new GreetingServer(new ServerSocket(6060));
        Thread serverTread = new Thread(greetingServer);
        serverTread.start();
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6060);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String response;
        String actual = null;
        while ((response = bufferedReader.readLine()) != null) {
            actual += response;
        }
        assertThat(actual, is(equalTo(expected)));
    }
}