package com.cloway.downloadagent;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Percentage {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    ProgressBar progressBar = context.mock(ProgressBar.class);

    @Test
    public void happyPath(){
        String urlString = "file:///home/clouway/workspaces/networking-and-gui/downloadagent/expected/picture-11.jpg";
        String downloadDir = "/home/clouway/workspaces/networking-and-gui/downloadagent/actual";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        context.checking(new Expectations() {{
            exactly(101).of(progressBar);
        }});

        downloadAgent.download(urlString, downloadDir);

    }
}
