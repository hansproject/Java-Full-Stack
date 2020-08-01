import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerWithKeys {

    static Logger logger = LoggerFactory.getLogger(ProducerWithKeys.class);

    public static void main(String [] args) {

        // Logger logger = LoggerFactory.getLogger(ProducerDemo.class);

        // Create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // Create the producer
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 10; i++) {
            // Create a producer record

            String topic = "first_topic";
            String value = "Hello Peter" + i;
            String key   = "id_" + i;

            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>(topic, key, value);

            logger.info("Key " + key );
            // Send data
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        logger.info("Received data \n"
                                + "Topic" + recordMetadata.topic()+ "\n"
                                + "Partition" + recordMetadata.partition()+ "\n"
                                + "Offset" + recordMetadata.offset() + "\n" );
                    } else {
                        e.printStackTrace();
                    }
                }
            });
        }
        producer.flush();
        producer.close();
    }
}
