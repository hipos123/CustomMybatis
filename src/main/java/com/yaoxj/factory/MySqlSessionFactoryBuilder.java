package com.yaoxj.factory;

import com.yaoxj.configuration.MyConfiguration;

import java.io.InputStream;

/**
 * Created by Administrator on 2019/9/6.
 */
public class MySqlSessionFactoryBuilder {

    public MySqlSessionFactory builder(InputStream inputStream) {
        MyConfiguration myConfiguration=new MyConfiguration(inputStream);

        return  new MySqlSessionFactory(myConfiguration);
    }
}
