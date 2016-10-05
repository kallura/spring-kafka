# spring-kafka

#### Example development of Kafka-based messaging solutions using Spring Kafka project.
#### Before running tests : 
###### 1. Download and extract Kafka using 7-zip from http://kafka.apache.org/downloads.html
###### 2. Go to your Kafka installation directory C:\kafka_2.11-0.9.0.0\
###### 3. Open a command prompt here. Now type: 
```
bin\windows\zookeeper-server-start.bat config\zookeeper.properties and press Enter.
```
###### 4. Open a command prompt here. Now type: 
```
bin\windows\kafka-server-start.bat config\server.properties and press Enter.
```
###### 5. Open a command prompt here. Now type :
```
bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic stringTopic
```
