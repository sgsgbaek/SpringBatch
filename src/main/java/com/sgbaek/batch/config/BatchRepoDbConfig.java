package com.sgbaek.batch.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class BatchRepoDbConfig extends DefaultBatchConfigurer {@Autowired

    @Override
    public void setDataSource(@Qualifier("batchDataSource") DataSource dataSource) {
        super.setDataSource(dataSource);
    }
}
