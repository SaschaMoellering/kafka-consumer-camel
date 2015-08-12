package io.autoscaling.components;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sascha.moellering on 12/08/2015.
 */
public class WorkerComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerComponent.class);

    public void submitJob(Exchange exchange) {
        Message inMessage = exchange.getIn();

        if (inMessage.getBody() instanceof String) {
            String body = (String) inMessage.getBody();

            LOGGER.debug("Reading message: " + body);
        } else {
            Object body = inMessage.getBody();
            LOGGER.info("Type of body: " + body.getClass().getName());
        }
    }
}
