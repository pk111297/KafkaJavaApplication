# KafkaJavaApplication

## Prerequisites:

Java 14  
Kafka 3  


## Running Application

First we need to run Kafka Server.  

For Running Kafka Server first run zookeeper by following command:

```bash
zookeeper-server-start.sh kafka_2.12-3.2.0/config/zookeeper.properties
```   
After Running Zookeper we need to start kafka server by running below command:

```bash
kafka-server-start.sh kafka_2.12-3.2.0/config/server.properties
```   

Now run the application
