package com.example.demo.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author kloudone
 *
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

	@Value("${db.driver}")
	private String DRIVER_CLASS_NAME;

	@Value("${db.url}")
	private String URL;

	@Value("${db.username}")
	private String USERNAME;

	@Value("${db.password}")
	private String PASSWORD;

	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.ddl-auto}")
	private String HIBERNATE_HBM2DDL_AUTO;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(datasource());
		sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		//hibernateProperties.put("hibernate.ddl-auto", HIBERNATE_HBM2DDL_AUTO);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	@Bean
	public DataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
}
