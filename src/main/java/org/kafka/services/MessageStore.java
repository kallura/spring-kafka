package org.kafka.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class MessageStore {

    private final BlockingQueue<ConsumerRecord<Integer, String>> records = new LinkedBlockingQueue<>();

    public void add(ConsumerRecord<Integer, String> record) {
        records.add(record);
    }

    public BlockingQueue<ConsumerRecord<Integer, String>> records() {
        return records;
    }
}
