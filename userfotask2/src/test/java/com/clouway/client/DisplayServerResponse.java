package com.clouway.client;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 * Created by clouway on 28.09.16.
 */
public class DisplayServerResponse {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private Display display = context.mock(Display.class);
    private FakeServer server = new FakeServer(6000, new Date(1000));
    private Thread fakeServer = new Thread(server);

    @Test
    public void happyPath() throws IOException {
        fakeServer.start();
        Socket socket = new Socket(InetAddress.getLocalHost(), 6000);
        ServerInputMonitor serverInputMonitor = new ServerInputMonitor(socket,display);
        context.checking(new Expectations() {{
            oneOf(display).display("Thu Jan 01 02:00:01 EET 1970");
        }});
        serverInputMonitor.run();
    }
}