package com.seveniu.consumer;

import com.seveniu.ConsumerServer;
import com.seveniu.conf.ConsumerConf;
import com.seveniu.conf.CrawlConf;
import com.seveniu.crawlClient.*;
import com.seveniu.def.TaskStatus;
import com.seveniu.node.Node;
import com.seveniu.pojo.Template;
import com.seveniu.service.DataService;
import com.seveniu.task.CrawlTaskStatistic;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by seveniu on 7/3/16.
 * TestConsumer
 */
@Component
public class DemoConsumer implements Consumer, InitializingBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ConsumerConf consumerConf;
    @Autowired
    CrawlConf crawlConf;
    @Autowired
    DataService dataService;

    private ConcurrentHashMap<Integer, Node> resultMap = new ConcurrentHashMap<>();
    private AtomicInteger count = new AtomicInteger();


    @Override
    public boolean has(String url) {
        return false;
    }

    @Override
    public void done(Node node) {
        logger.debug("done : {}", node.getUrl());
        dataService.insert(node);

    }

    @Override
    public void statistic(CrawlTaskStatistic statistic) {
        logger.info("statistic : {}", statistic);
    }

    @Override
    public void taskStatusChange(String s, TaskStatus taskStatus) {

        logger.info("task : {} status change to {}", s, taskStatus);
    }


    public void runTemplate(String url, Template template) {
        List<String> urls = new ArrayList<>(1);
        urls.add(url);
        String pages = template.getPages();
        TaskInfo taskInfo = new TaskInfo("" + count.get(), "demo-" + count.get(),
                "template-" + count.get(), urls, Proxy.OFF, Javascript.OFF, 1,
                pages, TemplateType.MULTI_LAYER_CONTENT, 1);

        try {
            CrawlClient.get().addTask(taskInfo);
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ConsumerConfig remoteConsumerConfig = new ConsumerConfig();
        remoteConsumerConfig.setName(consumerConf.getName());
        remoteConsumerConfig.setType(consumerConf.getType());
        remoteConsumerConfig.setHost(consumerConf.getHost());
        remoteConsumerConfig.setPort(consumerConf.getPort());
        try {
            ConsumerServer.start(crawlConf.getHost(), crawlConf.getPort(), this, remoteConsumerConfig);
            logger.info("consumer server start at port : {}", consumerConf.getPort());
        } catch (Exception e) {
            logger.error("consumer server start at port : {}", consumerConf.getPort());
        }
    }
}
