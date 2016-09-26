package com.cloway.downloadagent;


import org.junit.Test;

import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 12.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DownloadFileFromResource {

    private ProgressBar progressBar = (downloadedContent, size) -> System.out.println("Progress: "+ (downloadedContent * 100) / size+"%");

    @Test
    public void happyPath(){
        String urlString = "file:///home/clouway/workspaces/networking-and-gui/downloadagent/expected/picture-11.jpg";
        String downloadDir = "/home/clouway/workspaces/networking-and-gui/downloadagent/actual";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download(urlString, downloadDir);
        File file = new File("/home/clouway/workspaces/networking-and-gui/downloadagent/actual/picture-11.jpg");

        assertThat(file.isFile(), is(true));
    }


    @Test
    public void totalSpace(){
        String urlString = "file:///home/clouway/workspaces/networking-and-gui/downloadagent/expected/picture-11.jpg";
        String downloadDir = "/home/clouway/workspaces/networking-and-gui/downloadagent/actual";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download(urlString, downloadDir);
        File expected = new File("/home/clouway/workspaces/networking-and-gui/downloadagent/expected/picture-11.jpg");
        File actual = new File("/home/clouway/workspaces/networking-and-gui/downloadagent/actual/picture-11.jpg");
        assertThat(expected.getTotalSpace(), is(equalTo(actual.getTotalSpace())));
    }

    @Test(expected = UnreachableOrBrokenResource.class)
    public void emptyResource(){
        String urlString = "";
        String downloadDir = "/downloadagent/actual/picture-11.jpg";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);
        downloadAgent.download(urlString, downloadDir);

    }

    @Test(expected = DownloadDirectoryException.class)
    public void noDirProvided(){
        String urlString = "file:///home/clouway/workspaces/networking-and-gui/downloadagent/expected/picture-11.jpg";
        String downloadDir = "";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);
        downloadAgent.download(urlString, downloadDir);
    }
}