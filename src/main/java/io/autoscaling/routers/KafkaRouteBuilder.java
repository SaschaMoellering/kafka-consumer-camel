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
        String connectionString = "kafka:" + props.getKafkaBroker() + ":" + props.getKafkaPort() +
                "?topic=" + props.getKafkaTopic() + "&zookeeperHost=" + props.getZookeeperServer() +
                "&zookeeperPort=" + props.getZookeeperPort() + "&groupId=group1";
        from(connectionString)
                .bean(WorkerComponent.class, "submitJob");
    }
}
