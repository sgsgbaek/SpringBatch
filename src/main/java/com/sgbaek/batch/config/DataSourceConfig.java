package com.sgbaek.batch.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
@Configuration
@PropertySource({"classpath:application.yml"})
public class DataSourceConfig {

//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.job-repository-config")    //prefix 설정 사용
//    public HikariConfig batchHikariConfig() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.jpa-repository-config")    //prefix 설정 사용
//    public HikariConfig jpaHikariConfig() {
//        return new HikariConfig();
//    }

    /*Datasource 설정*/
    @Bean(name = "batchDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.job-repository-config")    //prefix 설정 사용
    public DataSource batchDataSource() {
        //return new HikariDataSource(batchHikariConfig());
        return DataSourceBuilder.create().build();
    }

    /*Datasource 설정*/
    @Bean(name = "jpaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.jpa-repository-config")    //prefix 설정 사용
    public DataSource jpaDataSource() {
        //return new HikariDataSource(jpaHikariConfig());
        return DataSourceBuilder.create().build();
    }
}
