package com.kafka.project;

import com.kafka.project.firstExample.ConsumerDemo;
import com.kafka.project.firstExample.ProducerDemo;
import com.kafka.project.firstExampleKeys.ProducerDemoKeys;
import com.kafka.project.firstExampleWithCallBack.ProducerDemoWithCallback;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutionException;

public class Main {
    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        logger.info("Kafka Demo Application started");
        //ProducerDemo.produce();
        //ProducerDemoWithCallback.produce();
        ProducerDemoKeys.produce();
        ConsumerDemo.consume();
        logger.info("Kafka Demo Application Completed");
    }
}