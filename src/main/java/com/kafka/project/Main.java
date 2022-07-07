package com.kafka.project;

import com.kafka.project.firstExample.ProducerDemo;
import com.kafka.project.firstExampleWithCallBack.ProducerDemoWithCallback;
import org.apache.log4j.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Kafka Demo Application started");
        //ProducerDemo.produce();
        ProducerDemoWithCallback.produce();
        logger.info("Kafka Demo Application Completed");
    }
}