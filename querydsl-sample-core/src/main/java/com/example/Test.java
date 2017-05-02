package com.example;

import com.example.db.querydsl.gen.QUser;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jack on 5/1/17.
 */
public class Test {

    @Autowired
    private  SQLQueryFactory sqlQueryFactory;

    public List<String> method(){

        QUser qUser = QUser.user;

        List<String> names = sqlQueryFactory.select(qUser.name).from(qUser).fetch();


        return names;
    }

    public static void main(String[] args) {

        new Test().method();
    }


}
