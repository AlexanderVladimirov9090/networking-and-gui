package com.clouway.client;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
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
 * Created by clouway on 15.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ClientConnection {

    @Test
    public void happyPath() throws IOException {
        String expected = "Server Response: Hello! Thu Jan 01 02:00:01 EET 1970\n";
        FakeServer fakeServer = new FakeServer(new ServerSocket(6000),new Date(1000));
        Thread serverThread = new Thread(fakeServer);

        serverThread.start();

        Client client = new Client(InetAddress.getLocalHost(), 6000, new RealDisplay());
        PrintStream originalOutput = new PrintStream(System.out);
        OutputStream outStream = new ByteArrayOutputStream();
        PrintStream prStream = new PrintStream(outStream);
        System.setOut(prStream);
        client.run();
        prStream.close();
        System.setOut(originalOutput);

        String actual = outStream.toString();

        assertThat(actual, is(equalTo(expected)));
    }
}