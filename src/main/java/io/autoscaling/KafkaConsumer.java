package io.autoscaling;

import io.autoscaling.routers.KafkaRouteBuilder;
import io.autoscaling.routers.RestRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

/**
 * Created by sascha.moellering on 18/05/2015.
 */
public class KafkaConsumer extends Main {

    public static void main(String[] args) throws Exception {
        KafkaConsumer main = new KafkaConsumer();

        main.enableHangupSupport();
        main.addRouteBuilder(createKafkaRouteBuilder());
        main.addRouteBuilder(createRestRouteBuilder());
        main.run(args);
    }

    static RouteBuilder createKafkaRouteBuilder() {
        return new KafkaRouteBuilder();
    }

    static RouteBuilder createRestRouteBuilder() {
        return new RestRouteBuilder();
    }
}
