package org.kafka.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kafka.conf.ApplicationConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.kafka.test.assertj.KafkaConditions.key;
import static org.springframework.kafka.test.assertj.KafkaConditions.partition;
import static org.springframework.kafka.test.assertj.KafkaConditions.value;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConf.class)
public class ProducerServiceTest {

    @Autowired
    private ProducerService producerService;
    @Autowired
    private MessageStore messageStore;

    @Test
    public void testSendDefaultWithPartition() throws Exception {
        producerService.sendDefault(0, 1, "message1");
        ConsumerRecord<Integer, String> record = messageStore.record();
        assertNotNull(record);
        assertThat(record).has(key(1));
        assertThat(record).has(partition(0));
        assertThat(record).has(value("message1"));
    }

    @Test
    public void testSendDefault() throws Exception {
        producerService.sendDefault(2, "message2");
        ConsumerRecord<Integer, String> record = messageStore.record();
        assertNotNull(record);
        assertThat(record).has(key(2));
        assertThat(record).has(value("message2"));
    }

    @Test
    public void testSend() throws Exception {
        producerService.send("stringTopic", 3, "message3");
        ConsumerRecord<Integer, String> record = messageStore.record();
        assertNotNull(record);
        assertThat(record).has(key(3));
        assertThat(record).has(value("message3"));

    }

    @Test
    public void testSendWithPartition() throws Exception {
        producerService.send("stringTopic", 0, 4, "message4");
        ConsumerRecord<Integer, String> record = messageStore.record();
        assertNotNull(record);
        assertThat(record).has(key(4));
        assertThat(record).has(partition(0));
        assertThat(record).has(value("message4"));
    }

    @Test
    public void testSendSpringIntegrationMessage() throws Exception {
        Message<String> message = new Message<String>() {
            @Override
            public String getPayload() {
                return "message5";
            }

            @Override
            public MessageHeaders getHeaders() {
                Map<String, Object> headers = new HashMap<>();
                headers.put(KafkaHeaders.TOPIC, "stringTopic");
                headers.put(KafkaHeaders.PARTITION_ID, 0);
                headers.put(KafkaHeaders.MESSAGE_KEY, 5);
                return new MessageHeaders(headers);
            }
        };
        producerService.send(message);
        ConsumerRecord<Integer, String> record = messageStore.record();
        assertNotNull(record);
        assertThat(record).has(key(5));
        assertThat(record).has(partition(0));
        assertThat(record).has(value("message5"));
    }

    @Test
    public void testSendWithCustomCallBack() throws Exception {
        producerService.sendWithCustomCallBack("stringTopic", 6, "message6");
        ConsumerRecord<Integer, String> record = messageStore.record();
        assertNotNull(record);
        assertThat(record).has(key(6));
        assertThat(record).has(value("message6"));
    }
}