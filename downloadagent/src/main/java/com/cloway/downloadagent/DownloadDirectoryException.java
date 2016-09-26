package com.cloway.downloadagent;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This exception is thrown when download directory is invalid or misssing.
 */
public class DownloadDirectoryException extends RuntimeException {
    DownloadDirectoryException(String message) {
        super(message);
    }
}