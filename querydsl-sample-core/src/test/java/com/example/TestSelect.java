package com.example;

import com.example.db.querydsl.gen.QAuthor;
import com.example.db.querydsl.gen.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by jack on 5/2/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, DataConfig.class})
public class TestSelect {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestSelect.class);

    private final QAuthor qAuthor = QAuthor.author;
    private final QUser qUser = QUser.user;

    @Autowired
    protected SQLQueryFactory sqlQueryFactory;

    @Before
    public void init(){

    }

    @org.junit.Test
    @Transactional
    public void testInsert() {

        sqlQueryFactory.insert(qAuthor).columns(qAuthor.id, qAuthor.name).values(7, "Vasa").execute();
    }

    @org.junit.Test
    public void testSelect(){

        List<String> autorNames = sqlQueryFactory.select(qAuthor.name).from(qAuthor).fetch();
        autorNames.forEach(LOGGER::info);
    }

    @org.junit.Test
    public void testSelectQUser(){

        List<Tuple> names = sqlQueryFactory.select(qUser.id, qUser.name).from(qUser).fetch();
        LOGGER.info(names.toString());
    }
}