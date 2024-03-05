package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfiguration {

    // 建立資料來源
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.myjdbc1")
    public DataSource myjdbc1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.myjdbc2")
    public DataSource myjdbc2DataSource() {
        return DataSourceBuilder.create().build();
    }

    //建立NamedParameterJdbcTemplate
    @Bean(name = "myjdbc1")
    public NamedParameterJdbcTemplate myjdbc1JdbcTemplate(
            @Qualifier("myjdbc1DataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    @Bean(name = "myjdbc2")
    public NamedParameterJdbcTemplate myjdbc2JdbcTemplate(
            @Qualifier("myjdbc2DataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    //建立交易管理
    @Bean
    DataSourceTransactionManager myjdbc1TransactionManager(
            @Qualifier("myjdbc1DataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    @Bean
    DataSourceTransactionManager myjdbc2TransactionManager(
            @Qualifier("myjdbc2DataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

}
