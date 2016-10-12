package com.clouway.informationserver;

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
public class GreetingServerTest {

    @Test
    public void happyPath() throws IOException{
        GreetingServer greetingServer = new GreetingServer(6070, new LinkedList<>(),new RealDisplay());
        Thread serverTread = new Thread(greetingServer);
        FakeClient fakeClient = new FakeClient(InetAddress.getLocalHost(), 6070);

        serverTread.start();
        fakeClient.connect();
        String actual = fakeClient.getResponse();

        assertThat(actual, is(equalTo("nullYou are client number: 1")));
    }
}