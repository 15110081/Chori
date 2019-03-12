package com.chori.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.chori.configuration" })
@PropertySources({
		@PropertySource("classpath:configurations/application.properties"),
		@PropertySource(value = "file:${chori_home}/application.properties", ignoreResourceNotFound = true) })
public class HibernateConfiguration {
	private static final Logger logger = Logger
			.getLogger(HibernateConfiguration.class);
	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		logger.info("getSessionFactory");
		try {
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			sessionFactory.setDataSource(dataSource());
			sessionFactory.setPackagesToScan(new String[] { "com.chori.model",
					"com.chori.entity" });
			sessionFactory.setHibernateProperties(hibernateProperties());
			return sessionFactory;
		} catch (Exception e) {
			logger.fatal("System error:" + e);
			throw e;
		}
	}

	@Bean
	public DataSource dataSource() {
		logger.info("dataSource");
		try {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(environment
					.getRequiredProperty("jdbc.driverClassName"));
			dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
			dataSource.setUsername(environment
					.getRequiredProperty("jdbc.username"));
			dataSource.setPassword(environment
					.getRequiredProperty("jdbc.password"));
			return dataSource;
		} catch (Exception e) {
			logger.fatal("System error:" + e);
			throw e;
		}
	}

	private Properties hibernateProperties() {
		logger.info("hibernateProperties");
		try {
			Properties properties = new Properties();
			properties.put("hibernate.dialect",
					environment.getRequiredProperty("hibernate.dialect"));
			properties.put("hibernate.show_sql",
					environment.getRequiredProperty("hibernate.show_sql"));
			properties.put("hibernate.format_sql",
					environment.getRequiredProperty("hibernate.format_sql"));
			// properties.put("hibernate.hbm2ddl.auto",
			// environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
			return properties;
		} catch (Exception e) {
			logger.fatal("System error:" + e);
			throw e;
		}
	}

	@Bean
	public DataSource dataSourceTest() {
		logger.info("dataSource");
		try {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(environment
					.getRequiredProperty("jdbc.driverClassNameTest"));
			dataSource.setUrl(environment.getRequiredProperty("jdbc.urlTest"));
			dataSource.setUsername(environment
					.getRequiredProperty("jdbc.usernameTest"));
			dataSource.setPassword(environment
					.getRequiredProperty("jdbc.passwordTest"));
			return dataSource;
		} catch (Exception e) {
			logger.fatal("System error:" + e);
			throw e;
		}
	}

	// private Properties hibernatePropertiesTest() {
	// logger.info("hibernateProperties");
	// try {
	// Properties properties = new Properties();
	// properties.put("hibernate.dialect",
	// environment.getRequiredProperty("hibernate.dialect"));
	// properties.put("hibernate.show_sql",
	// environment.getRequiredProperty("hibernate.show_sql"));
	// properties.put("hibernate.format_sql",
	// environment.getRequiredProperty("hibernate.format_sql"));
	// // properties.put("hibernate.hbm2ddl.auto",
	// // environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	// return properties;
	// } catch (Exception e) {
	// logger.fatal("System error:" + e);
	// throw e;
	// }
	// }

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		logger.info("transactionManager");
		try {
			HibernateTransactionManager txManager = new HibernateTransactionManager();
			txManager.setSessionFactory(s);
			return txManager;
		} catch (Exception e) {
			logger.fatal("System error:" + e);
			throw e;
		}
	}

	/**
	 * This function is used to initialize sample data when running app (insert
	 * some sample data automatically for testing)
	 * 
	 * @return
	 */
	// @Bean
	// public DataSourceInitializer dataSourceInitializer() {
	// ResourceDatabasePopulator resourceDatabasePopulator = new
	// ResourceDatabasePopulator();
	// resourceDatabasePopulator.addScript(new
	// ClassPathResource("/dataTest.sql"));
	// resourceDatabasePopulator.setSqlScriptEncoding("UTF-8");
	//
	// DataSourceInitializer dataSourceInitializer = new
	// DataSourceInitializer();
	// dataSourceInitializer.setDataSource(dataSource());
	// dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
	// return dataSourceInitializer;
	// }
}