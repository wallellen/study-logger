package edu.alvin.log4j;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.BeforeClass;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static java.util.Arrays.asList;

/**
 * The super class of all test class.
 */
public class BaseTest {

    // The database to create connection.
    private static DataSource dataSource;

    // The directory to put log files
    private static File logDirectory;

    @BeforeClass
    public static void configure() throws Exception {
        // clear the logs
        File workdir = new File("logs");
        if (workdir.exists() && workdir.isDirectory()) {
            if (workdir.listFiles() != null) {
                asList(workdir.listFiles()).forEach(File::delete);
            }
        }
        System.setProperty("WORKDIR", workdir.getAbsolutePath());
        createLogTables();
    }

    /**
     * Get the log file directory
     */
    public static File getLogDirectory() {
        // Open the log file from 'WORKDIR'
        if (logDirectory == null) {
            logDirectory = new File(System.getProperty("WORKDIR"));
        }
        return logDirectory;
    }

    /**
     * Get database connection
     */
    private static Connection getLocalConnection() throws Exception {
        if (dataSource == null) {
            Properties properties = new Properties();
            properties.load(BaseTest.class.getResourceAsStream("/connection.properties"));
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }
        return dataSource.getConnection();
    }

    /**
     * Read the log4j config file. the config file must in the root of classpath.
     */
    protected static void loadDomConfig(String config) {
        // Load the log4j XML configure file
        DOMConfigurator.configure(BaseTest.class.getResource("/" + config));
    }

    /**
     * Get database connection object
     */
    protected Connection getConnection() throws Exception {
        return getLocalConnection();
    }

    /**
     * Create log table in database.
     */
    protected static void createLogTables() throws Exception {
        try (Connection connection = getLocalConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            boolean hasTable = false;
            try (ResultSet rs = metaData.getTables(null, null, null, new String[] {"TABLE"})) {
                if (rs.next()) {
                    do {
                        if ("LOGGERS".equals(rs.getString("TABLE_NAME"))) {
                            connection.setAutoCommit(false);
                            try (Statement statement = connection.createStatement()) {
                                hasTable = true;
                                statement.execute("truncate table loggers");
                                connection.commit();
                            } catch (Exception e) {
                                connection.rollback();
                                throw e;
                            }
                        }
                    } while (rs.next());
                }
            }

            if (!hasTable) {
                try (Statement statement = connection.createStatement()) {
                    connection.setAutoCommit(false);
                    statement.execute(
                            "create table loggers(" +
                                    "id integer generated always as identity," +
                                    "time_stamp varchar(25)," +
                                    "thread varchar(20)," +
                                    "info_level varchar(10)," +
                                    "class varchar(50)," +
                                    "message varchar(1000)," +
                                    "primary key(id)" +
                                    ")"
                    );
                    connection.commit();
                } catch (Exception e) {
                    connection.rollback();
                    throw e;
                }
            }
        }
    }
}
