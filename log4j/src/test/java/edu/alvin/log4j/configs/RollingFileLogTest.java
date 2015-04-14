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
 * Logging in the file, and the file will be created automatic when the size of file is overflow.
 */
public class RollingFileLogTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(RollingFileLogTest.class);

    @Before
    public void setup() throws Exception {
        loadDomConfig("log4j-rolling-file.xml");
    }

    @Test
    public void testLog() {
        // write log more than 10K
        for (int i = 0; i < 10; i++) {
            LOG.debug("Test");
            LOG.info("Test");
            LOG.warn("Test", new SQLException());
            LOG.error("Test", new IOException());
            LOG.fatal("Test");
        }

        // Make sure the count of log files is more than one
        File[] logFiles = getLogDirectory().listFiles(pathname ->
                pathname.getName().startsWith("rolling-log"));
        assertThat(logFiles.length > 0, is(true));
    }
}
