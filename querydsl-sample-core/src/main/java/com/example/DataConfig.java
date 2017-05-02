package com.example;

import javax.sql.DataSource;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataConfig {

    @Autowired
    protected DataSource dataSource;

//    @Bean
//    public JdbcTemplate jdbcTempate() {
//        JdbcTemplate template = new JdbcTemplate();
//        template.setDataSource(this.dataSource);
//        return template;
//    }
//
//    @Bean
//    public QueryDslJdbcTemplate queryDslJdbcTemplate() {
//        QueryDslJdbcTemplate template = new QueryDslJdbcTemplate(
//                this.dataSource);
//        return template;
//    }

    /**
     * SQLQueryFactory bean config
     * @return
     */
    @Bean
    public SQLQueryFactory queryFactory(){

        SQLTemplates templates = new H2Templates();
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);

        SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, this.dataSource);
        return sqlQueryFactory;
    }
}
