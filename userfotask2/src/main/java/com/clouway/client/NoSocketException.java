package com.clouway.client;

/**
 * Created by clouway on 03.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class NoSocketException extends RuntimeException {

    public NoSocketException(String message){
        super(message);

    }
}