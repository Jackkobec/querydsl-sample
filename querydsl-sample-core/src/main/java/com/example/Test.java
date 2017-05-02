package com.example;

import com.example.db.querydsl.gen.Book;
import com.example.db.querydsl.gen.QBook;

import com.querydsl.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by jack on 5/1/17.
 */
public class Test {

    @Autowired
    DataSource dataSource;

    @Autowired
    protected QueryDslJdbcTemplate queryDslJdbcTemplate;

    public List<String> method(){

        SQLTemplates templates = new H2Templates();

        Configuration configuration = new Configuration(templates);

        SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, this.dataSource);

        QBook qBook = new QBook("book");

        List<String> books = sqlQueryFactory.select(qBook.isbn).fetch();


        return books;
    }

    public static void main(String[] args) {

        new Test().method();
    }


}
