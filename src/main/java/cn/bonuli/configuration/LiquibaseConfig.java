package cn.bonuli.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig {

	private static final Logger Logger = LoggerFactory.getLogger(LiquibaseConfig.class);

	@Bean
	public SpringLiquibase liquibase(DataSource dataSource) {
		SpringLiquibase lb = new SpringLiquibase();
		lb.setDataSource(dataSource);
		lb.setChangeLog("classpath:liquibase/purchase_sale_stock.xml");
		return lb;
	}

}
