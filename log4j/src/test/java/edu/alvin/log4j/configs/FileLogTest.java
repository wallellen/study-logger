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
 * Logging in the file
 */
public class FileLogTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(FileLogTest.class);

    @Before
    public void setup() throws Exception {
        loadDomConfig("log4j-file.xml");
    }

    @Test
    public void testLog() {
        LOG.debug("Test");
        LOG.info("Test");
        LOG.warn("Test", new SQLException());
        LOG.error("Test", new IOException());
        LOG.fatal("Test");

        // Make sure the log file is exist and not empty
        File[] logFiles = getLogDirectory().listFiles(pathname -> pathname.getName().equals("file-logs.log"));
        assertThat(logFiles.length == 1, is(true));
    }
}
