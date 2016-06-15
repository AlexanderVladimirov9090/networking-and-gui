package com.cloway.downloadagent;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This interface is used by Donwload Agent to comunicate with Real Implementation of Progress bar.
 */
<<<<<<< HEAD
=======

>>>>>>> 279caa4... Main Thread of the server now only accepts Client and makes two threads for reading and writing.
public interface ProgressBar {
    void update(long downloadedContent, long size);
}