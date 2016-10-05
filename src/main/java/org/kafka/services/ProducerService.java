package org.kafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private KafkaTemplate<Integer, String> template;

    public ListenableFuture<SendResult<Integer, String>> sendDefault(Integer key, String message) {
        return template.sendDefault(key, message);
    }

    public ListenableFuture<SendResult<Integer, String>> sendDefault(int partition, Integer key, String message) {
        return template.sendDefault(partition, key, message);
    }

    public ListenableFuture<SendResult<Integer, String>> send(String topic, Integer key, String message) {
        return template.send(topic, key, message);
    }

    public ListenableFuture<SendResult<Integer, String>> send(String topic, int partition, Integer key, String message) {
        return template.send(topic, partition, key, message);
    }

    public ListenableFuture<SendResult<Integer, String>> send(Message<String> message) {
        return template.send(message);
    }

    public ListenableFuture<SendResult<Integer, String>> sendWithCustomCallBack(String topic, Integer key, String message) {
        ListenableFuture<SendResult<Integer, String>> future = template.send(topic, key, message);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                logger.info("[onSuccess] -> topic : {}, key: {}", topic, key);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("[onFailure] -> topic : {}, key: {}, error: {}", topic, key, ex.getMessage(), ex);
            }
        });
        return future;
    }
}
