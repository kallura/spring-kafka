package org.kafka.conf;

import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkConf {

    @Bean
    ZkClient zkClient(@Value("${zookeeper.servers}") String zookeeperServers,
                      @Value("${session.timeout}") int sessionTimeOut,
                      @Value("${connection.timeout}") int connectionTimeOut) {
        return new ZkClient(zookeeperServers, sessionTimeOut, connectionTimeOut, ZKStringSerializer$.MODULE$);
    }

    @Bean
    ZkUtils zkUtils(@Value("${zookeeper.servers}") String zookeeperServers, ZkClient zkClient) {
        return new ZkUtils(zkClient, new ZkConnection(zookeeperServers), false);
    }
}
