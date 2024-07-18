package org.example.springex.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ProjectConfig {

	@Value("${custom.datasource.url}")
	private String datasourceUrl;

	@Value("${custom.datasource.username}")
	private String datasourceUsername;

	@Value("${custom.datasource.password}")
	private String datasourcePassword;

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setJdbcUrl(datasourceUrl);
		dataSource.setUsername(datasourceUsername);
		dataSource.setPassword(datasourcePassword);
		dataSource.setConnectionTimeout(1000);

		return dataSource;
	}

}
