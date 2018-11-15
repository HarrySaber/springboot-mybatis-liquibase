package cn.bonuli.configuration;

import cn.starpost.event.rabbitmq.RabbitMQConfig;
import cn.starpost.event.rabbitmq.RabbitMQEventBus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * RabbitMQEventBusFactory
 *
 * @author D.jin
 * @date 2018/6/29
 */
public class    RabbitMQEventBusFactory {
    static Logger logger = LoggerFactory.getLogger(RabbitMQEventBusFactory.class);

    static RabbitMQEventBusFactory factory = new RabbitMQEventBusFactory();

    private RabbitMQEventBus eventBus;

    private Map<String, RabbitMQEventBus> topicMapEventbus = Maps.newHashMap();

    private RabbitMQEventBusFactory() {
    }

    public void init(String topics) {
        try {
            logger.info("topics>>>:{}", topics);
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode array = (ArrayNode) mapper.readTree(topics);
            logger.info("topics>>>:{}", new ObjectMapper().valueToTree(array).toString());
            for (JsonNode json : array) {
                RabbitMQConfig mqconfig = new RabbitMQConfig(//
                        json.get("host").asText(), //
                        json.get("port").asInt(), //
                        json.get("username").asText(), //
                        json.get("password").asText(), //
                        json.get("virtualhost") == null ? null : json.get("virtualhost").asText() //
                );

                String topic = json.get("topic").asText();
                RabbitMQEventBus eb = new RabbitMQEventBus(mqconfig, topic);
                topicMapEventbus.put(topic, eb);
            }
            //logger.info("topicMapEventbus>>>:{}",topicMapEventbus);
        } catch (Exception e) {
            logger.error("Rabbit mq topics config error!", e);
        }
    }

    public static RabbitMQEventBusFactory getFactory() {
        return factory;
    }

    @Deprecated
    public RabbitMQEventBus getEventBus() {
        return eventBus;
    }

    public RabbitMQEventBus getEventBus(String topic) {
        logger.info("topicMapEventbus>>>:{}",topicMapEventbus);
        RabbitMQEventBus eb = topicMapEventbus.get(topic);
        if (eb == null) {
            throw new RuntimeException("not find topic:" + topic);
        }
        return eb;
    }
}
