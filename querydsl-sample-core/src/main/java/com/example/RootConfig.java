package com.example;

import javax.sql.DataSource;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Class for DataSource configuration.
 */
// Add auto configuration
@EnableAutoConfiguration
// Mark this class as configuration
@Configuration
class RootConfig {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(RootConfig.class);

    /**
     * Configure DataSource.
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        // Embedded database builder
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        // Chose type of embedded database - example h2
        embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2);

        // Add SQl script for auto fill our database
        embeddedDatabaseBuilder.addScript("classpath:sql/00_init.sql");// sql/00_init.sql in the querydsl-sample-db
        // Build database by previous settings
        EmbeddedDatabase embeddedDatabase = embeddedDatabaseBuilder.build();

        // Create DataSource with embedded data base
        Log4jdbcProxyDataSource log4jdbcProxyDataSource = new Log4jdbcProxyDataSource(embeddedDatabase);

        return log4jdbcProxyDataSource;
    }

}
