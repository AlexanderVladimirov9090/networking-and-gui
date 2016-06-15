package com.cloway.downloadagent;

<<<<<<< HEAD
=======

>>>>>>> 279caa4... Main Thread of the server now only accepts Client and makes two threads for reading and writing.
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DownloadAgent {
    private ProgressBar progressBar;

    public DownloadAgent(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    /**
     * Downloads file from url.
     *
     * @param urlString    given url string.
     * @param downloadPath download path.
     */
    public void download(String urlString, String downloadPath) {

        try {

            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            String fileName = url.getFile().substring(url.getFile().lastIndexOf("/"));
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(downloadPath + fileName);
            long sizeOfFile = urlConnection.getContentLength();
            long progress = 0;
            int percentage = getPercentage(urlConnection.getContentLength());
            byte[] buffer = new byte[percentage];
            int count = 0;

            while ((count = bis.read(buffer, 0, percentage)) != -1) {
                fis.write(buffer, 0, count);
                progress += buffer.length;
                progressBar.update(progress, sizeOfFile);
            }
            fis.close();
            bis.close();
        } catch (FileNotFoundException e) {
            throw new DownloadDirectoryException("Download directory missing");
        } catch (MalformedURLException e) {
            throw new UnreachableOrBrokenResource("Resource is unreachable or entered a broken resource.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getPercentage(long sizeOfFile) {
<<<<<<< HEAD

=======
>>>>>>> 279caa4... Main Thread of the server now only accepts Client and makes two threads for reading and writing.
        return (int) (sizeOfFile / 100);
    }
}