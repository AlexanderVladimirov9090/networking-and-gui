package com.clouway.client;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by clouway on 28.09.16.
 */
public class DisplayServerResponse {
    @Rule
    JUnitRuleMockery context = new JUnitRuleMockery();
    Display display = context.mock(Display.class);

    @Test
    public void happyPath() throws IOException {
        Client client = new Client(InetAddress.getLocalHost(), 6000,display);
        FakeServer fakeServer = new FakeServer(new ServerSocket(6000),new Date(1000));
        Thread thread = new Thread(fakeServer);
        thread.start();
        context.checking(new Expectations(){{
            oneOf(display).display("Server Response: Hello! Thu Jan 01 02:00:01 EET 1970\n");
            will(returnValue("Server Response: Hello! Thu Jan 01 02:00:01 EET 1970\n"));
        }});
        client.run();
    }
}
