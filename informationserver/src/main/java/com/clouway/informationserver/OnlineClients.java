package com.clouway.informationserver;

import java.util.List;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class OnlineClients {
    private final List<Client> onlineClients;


    public OnlineClients(List<Client> onlineClients) {
        this.onlineClients = onlineClients;

    }

    public void putClient() {
        Client client = new Client();
        onlineClients.add(client);
    }

    public int count() {
        return onlineClients.size();
    }

    private class Client {
    }
}
