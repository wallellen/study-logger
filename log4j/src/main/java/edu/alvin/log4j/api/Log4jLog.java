package edu.alvin.log4j.api;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.net.SMTPAppender;

import java.io.File;
import java.io.IOException;

/**
 * A demo class to demonstrates how to use log4j API
 */
public final class Log4jLog {

    private Log4jLog() {
    }

    /**
     * Get root logger instance, the other logger instance is created from root log.
     */
    public static Logger rootLog() {
        Logger rootLog = Logger.getRootLogger();
        // Check if the appender exist
        if (rootLog.getAppender("console") == null) {
            // Create the console appender with simple layout, let appender output the log by System.out
            ConsoleAppender appender = new ConsoleAppender(new SimpleLayout(), "System.out");
            // Set the name of this appender
            appender.setName("console");
            // Add the appender into root logger
            rootLog.addAppender(appender);
        }
        return rootLog;
    }

    /**
     * Get logger instance, with file appender.
     */
    public static Logger fileLog(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        if (logger.getAppender("file") == null) {
            try {
                // Create 'FileAppender', this appender can output logs into file
                FileAppender appender = new FileAppender(
                        new PatternLayout("[%d{dd HH:mm:ss,SSS} %-5p][%t][%c{3}] %m%n"),
                        new File(System.getProperty("WORKDIR"), "file-logs.log").getAbsolutePath());
                // Set the name of this appender
                appender.setName("file");
                // Set the size of buffer to cache the log in memory
                appender.setBufferSize(1024);
                // Set the character encoding
                appender.setEncoding("UTF-8");
                // Set output the log immediately
                appender.setImmediateFlush(true);
                // accept the settings of appender
                appender.activateOptions();
                // Add the appender into logger instance
                logger.addAppender(appender);
            } catch (IOException e) {
                rootLog().error(e);
            }
        }
        return logger;
    }

    /**
     * Get logger instance, with rolling file appender.
     */
    public static Logger rollingFileLog(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        if (logger.getAppender("rolling") == null) {
            try {
                // Create 'FileAppender', this appender can output logs into file,
                // and will create new file automatic when the current log file is overflow
                RollingFileAppender appender = new RollingFileAppender(
                        new PatternLayout("[%d{dd HH:mm:ss,SSS} %-5p][%t][%c{3}] %m%n"),
                        new File(System.getProperty("WORKDIR"), "rolling-logs.log").getAbsolutePath());
                // Set the new logs can append into log file
                appender.setAppend(true);
                // Set the name of this appender
                appender.setName("rolling");
                // Set how many log files can exist
                appender.setMaxBackupIndex(100);
                // Set the max size of each log file
                appender.setMaxFileSize("100KB");
                // Set the size of buffer to cache the log in memory
                appender.setBufferSize(1024);
                // Set the character encoding
                appender.setEncoding("UTF-8");
                // accept the settings of appender
                appender.activateOptions();
                // Add the appender into log instance
                logger.addAppender(appender);
            } catch (IOException e) {
                rootLog().error(e);
            }
        }
        return logger;
    }

    /**
     * Get logger instance, with daily rolling file appender.
     */
    public static Logger dailyRollingFileLog(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        if (logger.getAppender("daily-rolling") == null) {
            try {
                // Create 'FileAppender', this appender can output logs into file,
                // and will create new file automatic when the current log file is overflow
                DailyRollingFileAppender appender = new DailyRollingFileAppender(
                        new PatternLayout("[%d{dd HH:mm:ss,SSS} %-5p][%t][%c{3}] %m%n"),
                        new File(System.getProperty("WORKDIR"), "daily-rolling-logs.log").getAbsolutePath(),
                        "yyyy-MM-dd_HH_mm'.bak'");
                // Set the new logs will replace the old logs
                appender.setAppend(false);
                // Set name of this logger
                appender.setName("daily-rolling");
                // Set the size of buffer to cache the log in memory
                appender.setBufferSize(1024);
                // Set the size of buffer to cache the log in memory
                appender.setEncoding("UTF-8");
                // accept the settings of appender
                appender.activateOptions();
                // Add the appender into log instance
                logger.addAppender(appender);
            } catch (IOException e) {
                rootLog().error(e);
            }
        }
        return logger;
    }

    /**
     * Get logger instance, with daily rolling file appender and html layout
     */
    public static Logger htmlFileLog(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        if (logger.getAppender("html") == null) {
            try {
                // Create 'DailyRollingFileAppender', this appender can output logs into file,
                // and will create new file automatic when time is up
                DailyRollingFileAppender appender = new DailyRollingFileAppender(
                        new HTMLLayout(),
                        new File(System.getProperty("WORKDIR"), "html-logs.html").getAbsolutePath(),
                        "yyyy-MM-dd_HH_mm'-bak.html'");
                // Set the new logs will replace the old logs
                appender.setAppend(false);
                // Set name of this logger
                appender.setName("html");
                // Set the size of buffer to cache the log in memory
                appender.setBufferSize(1024);
                // Set the size of buffer to cache the log in memory
                appender.setEncoding("UTF-8");
                // accept the settings of appender
                appender.activateOptions();
                // Add the appender into log instance
                logger.addAppender(appender);
            } catch (IOException e) {
                rootLog().error(e);
            }
        }
        return logger;
    }

    /**
     * Get logger instance, with JDBC appender
     */
    public static Logger databaseLog(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        if (logger.getAppender("database") == null) {
            // Create 'DailyRollingFileAppender', this appender can record the log into database
            JDBCAppender appender = new JDBCAppender();
            // Set name of this logger
            appender.setName("database");
            // Set the JDBC diver
            appender.setDriver("org.apache.log4j.jdbc.JDBCAppender");
            // Set the URL of database
            appender.setURL("jdbc:derby://localhost:1527/log4j;create=true");
            // Set user name of database
            appender.setUser("app");
            // Set password of database
            appender.setPassword("app");
            // Set the size of buffer to cache the log in memory
            appender.setBufferSize(1024);
            // Set the SQL statement to record the log
            appender.setSql("insert into loggers(time_stamp,thread,info_level,class,message)" +
                    "values('%d','%t','%p','%c','%m')");
            // accept the settings of appender
            appender.activateOptions();
            // Add the appender into log instance
            logger.addAppender(appender);
        }
        return logger;
    }

    /**
     * Get logger instance, with SMTP appender
     */
    public static Logger emailLog(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz);
        if (logger.getAppender("email") == null) {
            // Create the appender, this appender will send log from email
            SMTPAppender appender = new SMTPAppender();
            // Set name of this logger
            appender.setName("email");
            // Set the size of buffer to cache the log in memory
            appender.setBufferSize(1024);
            // Set the host name of SMTP server
            appender.setSMTPHost("smtp.163.com");
            // Set user name of SMTP server
            appender.setSMTPUsername("alvintestmail@163.com");
            // Set password of SMTP server
            appender.setSMTPPassword("testmailbox2014.");
            // Set the address of email sender
            appender.setFrom("alvintestmail@163.com");
            // Set the address of email receiver
            appender.setTo("mousebaby8080@126.com");
            // Set the subject of current email
            appender.setSubject("Log Testing");
            // accept the settings of appender
            appender.activateOptions();     // this line is very important, if miss this line, email cannot be send
            // Set a layout instance to format the log content
            appender.setLayout(new PatternLayout("[%d{dd HH:mm:ss,SSS} %-5p][%t][%c{3}] %m%n"));
            // Set the threshold of this appender
            appender.setThreshold(Level.DEBUG);
            // Add the appender into log instance
            logger.addAppender(appender);
        }
        return logger;
    }
}
