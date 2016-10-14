package com.clouway.client;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 * This exceptions is thrown when server stops responding to client.
 */
class NoSocketException extends RuntimeException {

    NoSocketException(String message){
        super(message);
    }
}