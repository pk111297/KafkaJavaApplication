package com.kafka.project.firstExample;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

import java.util.Properties;

public class ProducerDemo {

    private static Logger logger = Logger.getLogger(ProducerDemo.class);
    public static void produce(){
        logger.info("Producing Kafka Events");
        //For producing we need to create properties and then create producer and then send data
        Properties properties = new Properties();
        //For setting properties refer to kafka documentation
        //https://kafka.apache.org/documentation/#producerconfigs
        String bootstrapServer = "127.0.0.1:9092";
        //old way
        /*properties.setProperty("bootstrap.servers",bootstrapServer);
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
         */
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //Producer
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(properties);

        //create ProducerRecord
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("first_topic","First Message");

        //send data
        kafkaProducer.send(producerRecord);
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
