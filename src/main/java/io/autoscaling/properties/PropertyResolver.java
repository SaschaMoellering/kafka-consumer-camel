package io.autoscaling.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Responsible for resolving properties file by the given file path.
 * <p/>
 * Created by sandra.kriemann on 24.02.2015.
 */
public abstract class PropertyResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyResolver.class);

    /**
     * Resolves the *.properties file from the classpath by the given file name.
     *
     * @param propFileName *.properties file name
     * @return loaded property instance
     */
    Properties getPropertiesFromClasspath(String propFileName) {
        Properties props = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(propFileName)) {

            if (inputStream == null) {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            props.load(inputStream);

            return props;
        } catch (IOException exc) {
            LOGGER.error("Not able to read properties", exc);
        }

        return null;
    }
}
