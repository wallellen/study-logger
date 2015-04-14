package edu.alvin.log4j.configs;

import edu.alvin.log4j.BaseTest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class PropertiesLogTest extends BaseTest {

    /**
     * Read the log4j config file. the config file must in the root of classpath.
     */
    protected static void loadPropertiesConfig(String config) {
        // Load the log4j properties configure file
        PropertyConfigurator.configure(BaseTest.class.getResource("/" + config));
    }

    @Before
    public void setup() {
        loadPropertiesConfig("log4j.properties");
    }

    /**
     * Test the root log
     */
    @Test
    public void test_rootLog() {
        Logger logger = Logger.getRootLogger();
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
    }

    @Test
    public void test_otherLog() throws Exception {
        Logger logger = Logger.getLogger(PropertiesLogTest.class);
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
    }
}