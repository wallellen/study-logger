package edu.alvin.log4j.api;

import edu.alvin.log4j.BaseTest;
import org.apache.log4j.Logger;
import org.apache.log4j.jdbc.JDBCAppender;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Log4jLogTest extends BaseTest {

    @Test
    public void test_rootLogger() {
        Logger logger = Log4jLog.rootLog();
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
    }

    @Test
    public void test_fileLogger() {
        Logger logger = Log4jLog.fileLog(this.getClass());
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
        assertThat(new File(getLogDirectory(), "file-logs.log").exists(), is(true));
    }


    @Test
    public void test_rollingFileLog() throws Exception {
        Logger logger = Log4jLog.rollingFileLog(this.getClass());
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
        assertThat(new File(getLogDirectory(), "rolling-logs.log").exists(), is(true));
    }

    @Test
    public void test_dailyRollingFileLog() throws Exception {
        Logger logger = Log4jLog.dailyRollingFileLog(this.getClass());
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
        assertThat(new File(getLogDirectory(), "daily-rolling-logs.log").exists(), is(true));
    }

    @Test
    public void test_testHtmlLog() throws Exception {
        Logger logger = Log4jLog.htmlFileLog(this.getClass());
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
        assertThat(new File(getLogDirectory(), "html-logs.html").exists(), is(true));
    }

    @Test
    public void test_databaseLog() throws Exception {
        Logger logger = Log4jLog.databaseLog(this.getClass());
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");

        JDBCAppender jdbcAppender = (JDBCAppender) logger.getAppender("database");
        jdbcAppender.flushBuffer();

        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("select count(1) from loggers")) {
                    rs.next();
                    assertThat(rs.getInt(1), Matchers.is(5));
                }
            }
        }
    }

    @Test
    public void test_emailLog() throws Exception {
        Logger logger = Log4jLog.emailLog(this.getClass());
        logger.debug("Test");
        logger.info("Test");
        logger.warn("Test", new SQLException());
        logger.error("Test", new IOException());
        logger.fatal("Test");
    }
}