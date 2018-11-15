package cn.bonuli.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class BeanConfig {

    private static final Logger Logger = LoggerFactory.getLogger(BeanConfig.class);

  /*  @Value("${tms.url}")
    private String tmsUrl;

    @Value("${starpost.url}")
    private String starpostUrl;
*/
  /*  @Bean
    public TmsClient tmsClient() {

        if (tmsUrl == null) {
            throw new RuntimeException("Unable Find Config[tms.url]");
        }
        TmsClient client = new TmsClient(tmsUrl);
        return client;
    }

    @Bean
    public StarpostClient starpostClient() {

        if (starpostUrl == null) {
            throw new RuntimeException("Unable Find Config[starpost.url]");
        }
        StarpostClient client = new StarpostClient(starpostUrl);
        return client;
    }*/


}
