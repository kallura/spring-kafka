package org.kafka.services;

import kafka.utils.ZkUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kafka.conf.ApplicationConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConf.class)
public class TopicServiceTest {

    @Autowired
    private ZkUtils zkUtils;

    @Autowired
    private TopicService topicService;

    @Before
    public void setUp() throws Exception {
        topicService.createTopic("testTopic", 3, 1);
    }

    @Test
    public void testCreateTopic() throws Exception {
        assertTrue(zkUtils.getAllTopics().contains("testTopic"));
    }

    @After
    public void tearDown() throws Exception {
        topicService.deleteTopic("testTopic");
    }
}