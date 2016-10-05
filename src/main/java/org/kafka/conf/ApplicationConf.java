package org.kafka.conf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableAutoConfiguration
@EnableKafka
@ComponentScan("org.kafka")
@Import({ProducerConf.class,
        ListenerContainerConf.class,
        ZkConf.class})
@PropertySource("classpath:kafka.properties")
public class ApplicationConf {

}
