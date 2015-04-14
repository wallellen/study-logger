package edu.alvin.log4j.configs;

import edu.alvin.log4j.BaseTest;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Logging in the file, and the file will be created automatic when the size of file is overflow.
 */
public class DatabaseLogTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(DatabaseLogTest.class);

    @Before
    public void setup() throws Exception {
        loadDomConfig("log4j-database.xml");
    }

    @Test
    public void testLog() throws Exception {
        // write log
        LOG.debug("Test");
        LOG.info("Test");
        LOG.warn("Test", new SQLException());
        LOG.error("Test", new IOException());
        LOG.fatal("Test");

        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("select count(1) from loggers")) {
                    rs.next();
                    assertThat(rs.getInt(1), is(5));
                }
            }
        }
    }
}
