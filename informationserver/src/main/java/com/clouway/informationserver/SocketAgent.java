package com.clouway.informationserver;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class  SocketAgent {
    private final List<Socket> sockets;

    public SocketAgent(List sockets) {
        this.sockets = sockets;
    }

    public synchronized void  put(Socket socket){
        sockets.add(socket);
    }

    public synchronized List<Socket> getSockets(){
        return new LinkedList<>(sockets);

    }


}
