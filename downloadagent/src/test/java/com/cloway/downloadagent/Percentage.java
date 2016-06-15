package com.cloway.downloadagent;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.net.URL;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Percentage {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private ProgressBar progressBar = context.mock(ProgressBar.class);

    @Test
    public void happyPath(){
        Class cls = DownloadFileFromResource.class;
        URL url = cls.getResource("/actual/12.jpg");
        URL download = cls.getResource("/expected");
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        context.checking(new Expectations() {{
            exactly(101).of(progressBar);
        }});

        downloadAgent.download("file://"+url.getFile(), download.getPath());
    }
}
