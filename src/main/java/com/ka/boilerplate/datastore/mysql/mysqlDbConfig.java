package com.ka.boilerplate.datastore.mysql;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Component
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory",
    transactionManagerRef = "mysqlTransactionManager",
    basePackages = {"com.ka.boilerplate.datastore.mysql.repo"})
public class mysqlDbConfig extends HikariConfig {

  @Autowired
  private Environment env;

  @Bean(name = "mysqlDatasource")
  @ConfigurationProperties(prefix = "mysql.datasource")
  public DataSource dataSource() {
    HikariConfig config = new HikariConfig();
    config.setConnectionTimeout(
        Long.parseLong(env.getProperty("mysql.datasource.hikari.connection-timeout")));
    config.setJdbcUrl(env.getProperty("mysql.datasource.hikari.jdbcUrl"));
    config.setMaximumPoolSize(
        Integer.parseInt(env.getProperty("mysql.datasource.hikari.maximum-pool-size")));
    config.setUsername(env.getProperty("mysql.datasource.username"));
    config.setPassword(env.getProperty("mysql.datasource.password"));
    return new HikariDataSource(config);
  }

  @Bean
  public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabasePlatform(env.getProperty("mysql.jpa.database-platform"));
    adapter.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
    adapter.setShowSql(Boolean.valueOf(env.getProperty("mysql.jpa.show-sql")));
    adapter.setGenerateDdl(Boolean.valueOf(env.getProperty("mysql.jpa.generate-ddl")));
    return adapter;
  }

  @Bean(name = "mysqlEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("mysqlDatasource") DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
        builder.dataSource(dataSource).packages("com.ka.boilerplate.datastore.mysql")
            .persistenceUnit("mysql").build();
    localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());

    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto",
        env.getProperty("mysql.jpa.hibernate.ddl-auto"));
    localContainerEntityManagerFactoryBean.setJpaProperties(properties);
    return localContainerEntityManagerFactoryBean;
  }

  @Bean(name = "mysqlTransactionManager")
  public PlatformTransactionManager mysqlTransactionManager(
      @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory mysqlEntityManagerFactory) {
    return new JpaTransactionManager(mysqlEntityManagerFactory);
  }
}
