package com.cloway.downloadagent;


import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

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

    private ProgressBar progressBar = (downloadedContent, size) -> System.out.println("Progress: " + (downloadedContent * 100) / size + "%");
    private Class cls = DownloadFileFromResource.class;

    @Test
    public void happyPath() throws IOException {
        URL url = cls.getResource("/actual/12.jpg");
        URL download = cls.getResource("/expected");
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download("file://" + url.getFile(), download.getPath());

        File actual = new File(download.getPath() + "/12.jpg");
        File expected = new File(url.getPath());

        byte[] actualBytes = new byte[(int) actual.length()];
        byte[] expectedBytes = new byte[(int) expected.length()];
        FileInputStream actualFileInputStream = new FileInputStream(actual);
        actualFileInputStream.read(actualBytes);
        actualFileInputStream.close();
        FileInputStream expectedFileInputStream = new FileInputStream(expected);
        expectedFileInputStream.read(expectedBytes);
        expectedFileInputStream.close();

        assertThat(expectedBytes, is(equalTo(actualBytes)));
    }

    @Test(expected = UnreachableOrBrokenResource.class)
    public void emptyResource() {
        URL download = cls.getResource("/expected");
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download("", download.getPath());
    }

    @Test(expected = DownloadDirectoryException.class)
    public void noDirProvided() {
        URL url = cls.getResource("/actual/12.jpg");
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download("file://" + url.getFile(), "");
    }
}