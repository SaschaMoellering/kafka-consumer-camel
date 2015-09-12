package io.autoscaling.routers;

import io.autoscaling.components.WorkerComponent;
import io.autoscaling.properties.ServerProperties;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by sascha.moellering on 18/05/2015.
 */

public class KafkaRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {

        ServerProperties props = ServerProperties.getInstance();

        String kafkaBroker = System.getenv().get("KAFKA_BROKER");
        String zookeeperServer = System.getenv().get("ZOOKEEPER_SERVER");

        if (null == kafkaBroker){
            kafkaBroker = props.getKafkaBroker();
        }

        if (null == zookeeperServer) {
            zookeeperServer = props.getZookeeperServer();
        }

        String connectionString = "kafka:" + kafkaBroker + ":" + props.getKafkaPort() +
                "?topic=" + props.getKafkaTopic() + "&zookeeperHost=" + zookeeperServer +
                "&zookeeperPort=" + props.getZookeeperPort() + "&groupId=group1";
        from(connectionString)
                .bean(WorkerComponent.class, "submitJob");
    }
}
