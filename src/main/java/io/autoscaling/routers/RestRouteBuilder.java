package io.autoscaling.routers;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by sascha.moellering on 19/05/2015.
 */
public class RestRouteBuilder extends RouteBuilder {

    private static final String PING_PONG = "{\"ping\":\"pong\"}";

    @Override
    public void configure() throws Exception {

        rest("cameltest").description("rest service for healthcheck")
                .produces("application/json")
                .get("/admin/healthcheck").to("direct:healthcheck");

        from("direct:healthcheck")
                .transform().simple(PING_PONG.toString());

        restConfiguration().component("jetty")
                .dataFormatProperty("prettyPrint", "true")
                .port(8080);
    }
}
