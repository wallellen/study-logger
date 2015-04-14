package edu.alvin.log4j2.configs;

import edu.alvin.log4j2.rules.ConfigureRule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;


public class AsyncLogTest {

    @Rule
    public ConfigureRule rule = new ConfigureRule("configs/async-logs.xml");

    @Test
    public void test() throws Exception {
        Logger logger = LogManager.getLogger(AsyncLogTest.class);
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
    }
}
