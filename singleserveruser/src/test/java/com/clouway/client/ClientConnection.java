package com.clouway.client;

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String expected = "Server Response: Hello! " + dateFormat.format(new Date()) + "\n";
        Server server = new Server(new ServerSocket(6000));
        Thread serverThread = new Thread(server);

        serverThread.start();

        Client client = new Client(InetAddress.getLocalHost(), 6000);
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