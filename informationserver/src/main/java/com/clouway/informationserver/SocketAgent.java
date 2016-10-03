package com.clouway.informationserver;

import java.net.Socket;
import java.util.List;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class SocketAgent {
    private final List<Socket> sockets;

    public SocketAgent(List sockets) {
        this.sockets = sockets;
    }

    public void put(Socket socket){
        sockets.add(socket);
    }

    public List<Socket> getSockets(){
        return sockets;
    }
}
