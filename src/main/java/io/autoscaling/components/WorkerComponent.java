package io.autoscaling.components;

import com.google.protobuf.InvalidProtocolBufferException;
import io.autoscaling.proto.AddressBookProtos;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

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
            try {
                byte[] body = (byte[])inMessage.getBody();

                AddressBookProtos.AddressBook.Builder addressBookBuilder = AddressBookProtos.AddressBook.newBuilder();
                addressBookBuilder.mergeFrom(body);
                AddressBookProtos.AddressBook addressBook = addressBookBuilder.build();
                LOGGER.info(addressBook.toString());
            }
            catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
    }
}
