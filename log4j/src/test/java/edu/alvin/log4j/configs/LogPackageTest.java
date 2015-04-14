package edu.alvin.log4j.configs;

import edu.alvin.log4j.BaseTest;
import edu.alvin.log4j.api.Log4jLogTest;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Test show log in console.
 */
public class LogPackageTest extends BaseTest {

    @Before
    public void setup() throws Exception {
        loadDomConfig("log4j-package.xml");
    }

    @Test
    public void test_logvalid() {
        final Logger logger = Logger.getLogger(LogPackageTest.class);
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
    }

    @Test
    public void test_loginvalid() {
        final Logger logger = Logger.getLogger(Log4jLogTest.class);
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
    }
}