package com.example;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataConfig {

    /**
     * Connect DataSource bean from RootConfig
     */
    @Autowired
    protected DataSource dataSource;

    /**
     * SQLQueryFactory bean config for use querying
     *
     * @return
     */
    @Bean
    public SQLQueryFactory queryFactory() {

        // Chose template for our database (we use h2 database)
        SQLTemplates templates = new H2Templates();
        // Send chosen template to the com.querydsl.sql.Configuration
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);

        // Create SQLQueryFactory with com.querydsl.sql.Configuration and DataSource
        SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, this.dataSource);

        return sqlQueryFactory;
    }
}
