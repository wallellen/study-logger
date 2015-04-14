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
 * Logging in the file, and the file would be created automatic when the time is up.
 */
public class DailyRollingFileLogTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(DailyRollingFileLogTest.class);

    @Before
    public void setup() throws Exception {
        loadDomConfig("log4j-daily-rolling-file.xml");
    }

    @Test
    public void testLog() {
        LOG.debug("Test");
        LOG.info("Test");
        LOG.warn("Test", new SQLException());
        LOG.error("Test", new IOException());
        LOG.fatal("Test");

        // Make sure the count of log files is more than one
        File[] logFiles = getLogDirectory().listFiles(pathname ->
                pathname.getName().startsWith("daily-rolling-log"));
        assertThat(logFiles.length == 1, is(true));
    }
}
