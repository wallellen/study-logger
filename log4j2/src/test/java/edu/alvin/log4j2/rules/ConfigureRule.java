package edu.alvin.log4j2.rules;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.io.InputStream;

/**
 * The rule to load log4j config file before the testing method execute.
 */
public class ConfigureRule implements MethodRule {
    private String configFileName;

    /**
     * Set the log4j config file.
     */
    public ConfigureRule(String configFileName) {
        this.configFileName = configFileName;
    }

    /**
     * Before testing method execute.
     */
    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        try {
            // Load the config file
            InputStream in = ConfigureRule.class.getResourceAsStream("/" + configFileName);
            ConfigurationSource source = new ConfigurationSource(in);
            // Initialize log4j
            Configurator.initialize(null, source);
        } catch (IOException e) {
            throw new IllegalAccessError();
        }
        return base;
    }
}
