package deeplearning4j_scaleout.dl4j_streaming.src.main.java.org.deeplearning4j.streaming.kafka;
//package org.deeplearning4j.streaming.kafka;

import lombok.Builder;
import lombok.Data;

import org.apache.commons.codec.StringEncoder;

/**
 * Kafka uri builder
 *
 * @author Adam Gibson
 */
@Data
@Builder
public class KafkaUriBuilder {
    private String kafkaBroker;
    private String consumingTopic;
    private String groupId;
    private String zooKeeperHost;
    private int zooKeeperPort;

    public String uri() {
        return String.format("kafka://%s?topic=%s&groupId=%s&zookeeperHost=%s&zookeeperPort=%d&serializerClass=%s&keySerializerClass=%s",
                kafkaBroker,
                consumingTopic
                ,groupId
                ,zooKeeperHost
                ,zooKeeperPort,
                StringEncoder.class.getName(),
                StringEncoder.class.getName());
    }
}
