package io.autoscaling.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by sandra.kriemann on 24.02.2015.
 */
public final class ServerProperties extends PropertyResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProperties.class);
    private static final String KAFKA_BROKER = "kafka.broker";
    private static final String KAFKA_TOPIC = "kafka.topic";
    private static final String KAFKA_PORT = "kafka.port";
    private static final String ZOOKEEPER_SERVER = "zookeeper.server";
    private static final String ZOOKEEPER_PORT = "zookeeper.port";

    private static ServerProperties instance = null;

    private String kafkaBroker;
    private String kafkaTopic;
    private Integer kafkaPort;
    private String zookeeperServer;
    private Integer zookeeperPort;

    private ServerProperties() {
        String stage = System.getProperty("stage");

        if (stage == null) {
            stage = System.getenv("stage");
        }
        LOGGER.info("Stage Environment: " + stage);


        if (stage == null || stage.trim().length() == 0) {
            LOGGER.info("NO ENVIRONMENT STAGE HAS BEEN SET, SWITCHING TO LOCAL!");
            stage = "local";
        }

        // Load a properties file
        Properties props = getPropertiesFromClasspath("server_" + stage + ".properties");

        if (props != null) {
            kafkaTopic = props.getProperty(KAFKA_TOPIC);
            kafkaBroker = props.getProperty(KAFKA_BROKER);
            kafkaPort = Integer.valueOf(props.getProperty(KAFKA_PORT));
            zookeeperServer = props.getProperty(ZOOKEEPER_SERVER);
            zookeeperPort = Integer.valueOf(props.getProperty(ZOOKEEPER_PORT));
        }
    }

    /**
     * @return synchronized singleton instance of ServerProperties
     */
    public static ServerProperties getInstance() {
        if (instance == null) {
            synchronized (ServerProperties.class) {
                if (instance == null) {
                    instance = new ServerProperties();
                }
            }
        }
        return instance;
    }

    public String getKafkaBroker() {
        return kafkaBroker;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public Integer getKafkaPort() {
        return kafkaPort;
    }

    public String getZookeeperServer() {
        return zookeeperServer;
    }

    public Integer getZookeeperPort() {
        return zookeeperPort;
    }
}
