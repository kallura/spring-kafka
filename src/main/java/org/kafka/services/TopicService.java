package org.kafka.services;

import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Properties;

@Service
public class TopicService {

    private static final Logger logger = LoggerFactory.getLogger(TopicService.class);

    @Autowired
    private ZkUtils zkUtils;
    @Autowired
    private ZkClient zkClient;

    public void createTopic(String topicName, int numPartitions, int replicationFactor) {
        try {
            Properties topicConfiguration = new Properties();
            AdminUtils.createTopic(zkUtils, topicName, numPartitions, replicationFactor, topicConfiguration);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void deleteTopic(String topicName) {
        try {
            AdminUtils.deleteTopic(zkUtils, topicName);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void addPartition(String topicName, int numPartitions, String replicaAssignmentStr) {
        try {
            AdminUtils.addPartitions(zkUtils, topicName, numPartitions, replicaAssignmentStr, true);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @PreDestroy
    public void cleanUp() {
        if (zkClient != null) {
            zkClient.close();
        }
    }
}
