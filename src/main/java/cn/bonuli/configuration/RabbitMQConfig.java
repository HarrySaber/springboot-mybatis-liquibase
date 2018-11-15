package cn.bonuli.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQConfig
 *
 * @author D.jin
 * @date 2018/6/29
 */
@Configuration
public class RabbitMQConfig {

    private static final Logger Logger = LoggerFactory.getLogger(RabbitMQConfig.class);
/*

    @Value("${topics}")
    private String topics;

    @Bean
    public RabbitMQEventBusFactory rabbitMQinit(){
       // Logger.info("topics>>>:{}",topics);
        RabbitMQEventBusFactory rabbitMQEventBusFactory = RabbitMQEventBusFactory.getFactory();
        rabbitMQEventBusFactory.getFactory().init(topics);
        return  rabbitMQEventBusFactory;
    }
*/
}
