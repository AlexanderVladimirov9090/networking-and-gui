package com.clouway.server;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
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
    public void happyPath() throws IOException {

        String expected = "Server started:\n" + "Server stopped!\n";
        GreetingServer greetingServer = new GreetingServer(new ServerSocket(6060));

        Client client = new Client(InetAddress.getLocalHost(), 6060);
        Thread clientThread = new Thread(client);
        clientThread.start();

        PrintStream originalOutput = new PrintStream(System.out);
        OutputStream outStream = new ByteArrayOutputStream();
        PrintStream prStream = new PrintStream(outStream);
        System.setOut(prStream);
        greetingServer.accept();
        prStream.close();

        System.setOut(originalOutput);
        String actual = outStream.toString();
        assertThat(actual, is(equalTo(expected)));
    }

}