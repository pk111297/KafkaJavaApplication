package com.kafka.project.firstExample;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {
    private static Logger logger = Logger.getLogger(ConsumerDemo.class);

    public static void consume(){
        Properties properties = new Properties();
        String bootstrapServer = "127.0.0.1:9092";
        String groupId = "second_group";
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        //subscribing for topics
        //need to pass collections of topics which we need to subscribe
        //consumer.subscribe(Collections.singleton("second_topic"));
        consumer.subscribe(Arrays.asList("first_topic","second_topic"));

        //asking for data
        while(true){
            //consumer.poll(1000);
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord record: records){
                logger.info("Data fetched is:\n" + "Key: " + record.key() + "\nValue: "+ record.value()
                        +"\nPartition" + record.partition() + "\nOffset: " + record.offset());
            }
        }

    }
}
