package com.sgbaek.batch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:application.yml"})
@EnableJpaRepositories(
        basePackages = "com.sgbaek.batch.repository",    //repository 경로
        entityManagerFactoryRef = "jpaEntityManager",       //LocalContainerEntityManagerFactoryBean 함수
        transactionManagerRef = "jpaTransactionManager"
)
public class JpaRepoDbConfig {

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setShowSql(true);
        return hibernateJpaVendorAdapter;
    }

    /*Entity Manager 설정*/
    @Autowired
    @Bean(name = "jpaEntityManager")
    //@Primary
    @ConfigurationProperties(prefix = "spring")    //prefix 설정 사용
    public LocalContainerEntityManagerFactoryBean jpaEntityManager(@Qualifier("jpaDataSource")DataSource dataSource){
        //엔티티매니저 팩토리빈 생성
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);  //datasource 주입
        em.setPackagesToScan(new String[] {"com.sgbaek.batch.entity"});     //Entity 스캔 할 패키지 경로
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter());  //Hibernate 주입
        em.afterPropertiesSet();
        return em;
    }

    /*Transaction Manager 설정*/
    @Bean(name = "jpaTransactionManager")
    //@Primary
    @Autowired
    public PlatformTransactionManager jpaTransactionManager(@Qualifier("jpaEntityManager") LocalContainerEntityManagerFactoryBean em){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(em.getObject());
        return transactionManager;
    }
}
