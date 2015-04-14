package edu.alvin.log4j.configs;

import edu.alvin.log4j.BaseTest;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Test show log in console.
 */
public class AsyncLogTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(AsyncLogTest.class);

    @Before
    public void setup() throws Exception {
        loadDomConfig("log4j-async.xml");
    }

    @Test
    public void testLog() {
        LOG.debug("Test");
        LOG.info("Test");
        LOG.warn("Test", new SQLException());
        LOG.error("Test", new IOException());
        LOG.fatal("Test");

        System.out.println("process is finished...");
    }
}