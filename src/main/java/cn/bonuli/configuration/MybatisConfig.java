package cn.bonuli.configuration;

import cn.bonuli.values.JdbcConnectionSettings;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据库配置
 * 
 * @author lijun
 *
 */
@Configuration
@EnableConfigurationProperties({ JdbcConnectionSettings.class })
@MapperScan(basePackages = "cn.bonuli.mappers", sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {

	final static Logger Logger = LoggerFactory.getLogger(MybatisConfig.class);

	@Autowired
	private JdbcConnectionSettings jdbcConf;

	@Bean
	public DataSource dataSource() {
		if (jdbcConf.getDriver() == null) {
			throw new RuntimeException("jdbc config error");
		}
		HikariConfig conf = new HikariConfig();
		conf.setDriverClassName(jdbcConf.getDriver());
		conf.setJdbcUrl(jdbcConf.getUrl());
		conf.setUsername(jdbcConf.getUsername());
		conf.setPassword(jdbcConf.getPassword());
		conf.setMinimumIdle(jdbcConf.getMinConn());
		conf.setMaximumPoolSize(jdbcConf.getMaxConn());

		HikariDataSource ds = new HikariDataSource(conf);
		return ds;
	}

}
