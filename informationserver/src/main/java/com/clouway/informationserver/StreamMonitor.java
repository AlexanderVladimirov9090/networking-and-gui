package com.clouway.informationserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by clouway on 12.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class StreamMonitor implements Runnable {
    private final SocketAgent socketAgent;
    private BufferedReader bufferedReader;
    private final Display display;


    public StreamMonitor(SocketAgent socketAgent, Display display) {
        this.socketAgent = socketAgent;
        this.display = display;
    }

    @Override
    public void run() {

        try {
            while (true) {
                readFromClients();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readFromClients() throws IOException {
       int numberOfClient =  socketAgent.getSockets().size();
        bufferedReader = new BufferedReader(new InputStreamReader(socketAgent.getSockets().get(--numberOfClient).getInputStream()));
        String response;
        response = bufferedReader.readLine();
        if(response!=null) {
            display.display(numberOfClient + ": " + response);
        }
    }
}