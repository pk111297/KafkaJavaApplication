package com.kafka.project.firstExampleKeys;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {

    private static Logger logger = Logger.getLogger(ProducerDemoKeys.class);
    public static void produce() throws ExecutionException, InterruptedException {
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
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("second_topic","First Message");

        //send data with callback function
        kafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                //Executes every time when record is successfully send or exception is thrown
                if(null!=e){
                    logger.error("Error in Execution of Kafka with error as {}",e);
                }
                else{
                    logger.info("Kafka Executed Successfully with Record metadata as:\n"
                            + "OFFSET: " + recordMetadata.offset() + "\nPARTITION: " +recordMetadata.partition()
                            + "\nTOPIC: " + recordMetadata.topic() + "\nTIMESTAMP: " + recordMetadata.timestamp());
                }
            }
        });
        for(int i=0; i<10;++i){
            String key = "Id_" +i;
            //create ProducerRecord
            producerRecord = new ProducerRecord<>("second_topic",key,"First Message from "+i);

            logger.info("Key: " + key);

            //send data with callback function
            kafkaProducer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    //Executes every time when record is successfully send or exception is thrown
                    if(null!=e){
                        logger.error("Error in Execution of Kafka with error as {}",e);
                    }
                    else{
                        logger.info("Kafka Executed Successfully with Record metadata as:\n"
                                + "OFFSET: " + recordMetadata.offset() + "\nPARTITION: " +recordMetadata.partition()
                                + "\nTOPIC: " + recordMetadata.topic() + "\nTIMESTAMP: " + recordMetadata.timestamp());
                    }
                }
            }).get();  //block to .send() to make it synchronous
        }

        kafkaProducer.flush();
        kafkaProducer.close();
    }
}

/*2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_0 PARTITION: 2
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_1 PARTITION: 1
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_2 PARTITION: 2
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_3 PARTITION: 0
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_4 PARTITION: 1
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_5 PARTITION: 2
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_6 PARTITION: 0
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_7 PARTITION: 2
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_8 PARTITION: 1
2022-07-07 17:38:10 INFO  ProducerDemoKeys:55 - Key: Id_9 PARTITION: 0
*/