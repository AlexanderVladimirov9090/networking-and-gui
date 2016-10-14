package com.clouway.server;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 26.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class GreetingServerTest {
    private FakeClient client = new FakeClient(InetAddress.getLocalHost(), 6060);
    private String expected = generateExpectedResponse();

    public GreetingServerTest() throws UnknownHostException {
    }

    @Test
    public void happyPath() throws IOException, InterruptedException {
        GreetingServer greetingServer = new GreetingServer(new ServerSocket(6060));
        Thread serverTread = new Thread(greetingServer);

        serverTread.start();
        client.connect();

        String actual = client.getResponse();

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
}