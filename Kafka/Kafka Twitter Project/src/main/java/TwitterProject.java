import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TwitterProject {

    static Logger logger = LoggerFactory.getLogger(ProducerDemo.class);

    public static String getHomePageTweets(){

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("Hh2xwC7SWZaVOimVI3PKIvE4K")
                .setOAuthConsumerSecret("qPJqYQumNuu6tVgNwMniOJLXzk9AsLDes9EaclsivvkuCqVsZK")
                .setOAuthAccessToken("2418719884-vQ7Hz55Gl92ufgPqKjq5AH2vS7sCia18NYiDEhu")
                .setOAuthAccessTokenSecret("6r9OvYHd1TZVqZL7fIXRHo9EkmsQUQsjHkPmPEoNOM3bm");
        TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
        Twitter tw = tf.getInstance();
        String text = "";
        try {
            List<Status> statusList = tw.getHomeTimeline();

            for(Status status:statusList){
                text = text + status.getText();
               //System.out.println(status.getUser().getName() + ": " + status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void sendRecord(String topicName, String value, String groupId){
        // Create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG,groupId);
        properties.setProperty("value.serializer",StringSerializer.class.getName() );

        // Add some properties to the producer
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,"true");
        properties.setProperty(ProducerConfig.ACKS_CONFIG,"all");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,"5");
        // Add compression
        // Snappy is a good compression algorithm for text and JSON objects
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");
        // Add a 20 ms delay or latency
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG,"20");
        // Set Batch size to 32KB. The default is 16KB
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32*1024));


        // Create the producer
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // Create a producer record
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>(topicName, value);

        // Send data
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e==null){
                    logger.info("Data sent!!!");
                }
                else{
                    e.printStackTrace();
                }
            }
        });

        producer.flush();
        producer.close();
    }

    public static void printData(String topicName, String groupId){

        String groupID = groupId;
        String topic   = topicName;
        // Create Consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // Subscribe a consumer to the topic
        consumer.subscribe(Arrays.asList(topic));

        while (true){

            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String,String> record: records){

                logger.info("Received data \n"
                        + "Topic's name: " + record.topic()+ "\n"
                        + "Data " + record.value() + "\n"
                        + "Partition" + record.partition()+ "\n"
                        + "Offset" + record.offset() + "\n" );
            }
        }
    }

    public void run(){

    }

    public static void main(String[] args) {

        String textToSend = getHomePageTweets();
        String topic      = "second_topic";
        String groupId    = "3rd_App";
        sendRecord(topic, textToSend,groupId);
        printData(topic,groupId);
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            logger.info("Application is stopping");
        }));
    }
}
