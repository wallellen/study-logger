package edu.alvin.log4j.configs;

import edu.alvin.log4j.BaseTest;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Logging as HTML
 */
public class HtmlLogTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(HtmlLogTest.class);

    @Before
    public void setup() throws Exception {
        loadDomConfig("log4j-html.xml");
    }

    @Test
    public void testLog() {
        LOG.debug("Test");
        LOG.info("Test");
        LOG.warn("Test", new SQLException());
        LOG.error("Test", new IOException());
        LOG.fatal("Test");

        // Make sure the count of log files is more than one
        File[] logFiles = getLogDirectory().listFiles(pathname -> pathname.getName().startsWith("html-log"));
        assertThat(logFiles.length == 1, is(true));
    }
}
