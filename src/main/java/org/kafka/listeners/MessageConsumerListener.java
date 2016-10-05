package org.kafka.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kafka.services.MessageStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumerListener.class);

    @Autowired
    private MessageStore messageStore;

    @KafkaListener(id = "defaultTopic", topics = "stringTopic")
    public void listenDefault(ConsumerRecord<Integer, String> record) {
        messageStore.add(record);
        logger.info("[listen] -> :" + record.topic() + ". Message : " + record.value() +
                " was received!. Offset: " + record.offset());
    }
}
