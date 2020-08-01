import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    static Logger logger = LoggerFactory.getLogger(ProducerDemo.class);

    public static void main(String [] args){

        // Logger logger = LoggerFactory.getLogger(ProducerDemo.class);

        // Create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName() );

        // Create the producer
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // Create a producer record
        ProducerRecord <String, String> record =
                new ProducerRecord<String, String>("first_topic", "I am Hans");

        // Send data
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e==null){
                    logger.info("Received data \n" + recordMetadata.topic());
                }
                else{
                    e.printStackTrace();
                }
            }
        });

        producer.flush();
        producer.close();
    }
}
