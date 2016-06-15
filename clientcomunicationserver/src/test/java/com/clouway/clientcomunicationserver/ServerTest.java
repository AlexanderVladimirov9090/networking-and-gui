package com.clouway.clientcomunicationserver;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ServerTest {

    @Test
    public void happyPath() throws IOException{
        Server server = new Server(6070, new LinkedList<>(),new ConsoleDisplay());
        Thread serverTread = new Thread(server);
        FakeClient fakeClient = new FakeClient(InetAddress.getLocalHost(), 6070, "");
        String expected = "nullYou are client number: 1";
        serverTread.start();
        fakeClient.connect();
        String actual = fakeClient.getResponse();

        assertThat(actual, is(equalTo(expected)));
    }
}