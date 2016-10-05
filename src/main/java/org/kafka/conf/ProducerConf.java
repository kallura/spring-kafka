package org.kafka.conf;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConf {

    @Bean
    public ProducerFactory<Integer, String> producerFactory(@Value("${bootstrap.servers}") String servers,
                                                            @Value("${client.id}") String clientId,
                                                            @Value("${key.serializer}") String keySerializer,
                                                            @Value("${value.serializer}") String valueSerializer) {
        return new DefaultKafkaProducerFactory<>(producerConfigs(servers, clientId, keySerializer, valueSerializer));
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate(ProducerFactory<Integer, String> producerFactory,
                                                        @Value("${default.topic}") String defaultTopic) {
        KafkaTemplate<Integer, String> kafkaTemplate = new KafkaTemplate<>(producerFactory, true);
        kafkaTemplate.setDefaultTopic(defaultTopic);
        return kafkaTemplate;
    }


    private Map<String, Object> producerConfigs(String servers, String clientId, String keySerializer,
                                                String valueSerializer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return props;
    }
}
