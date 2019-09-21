package com.yaoxj.configuration;

import java.io.InputStream;
import java.util.EventListener;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/6.
 */
public class MyConfiguration {
    //配置文件里面主要存放2个对象，一个是数据的环境配置信息，一个是sql的xml解析对象。
    private MyEnviroment myEnviroment;
    private Map<String,MyStatementSql> myStatementSqlMap;

    public MyConfiguration(InputStream inputStream) {
        //通过这个输入流，开始构造环境对象和statementMap对象

    }

    public MyEnviroment getMyEnviroment() {
        return myEnviroment;
    }

    public void setMyEnviroment(MyEnviroment myEnviroment) {
        this.myEnviroment = myEnviroment;
    }

    public Map<String, MyStatementSql> getMyStatementSqlMap() {
        return myStatementSqlMap;
    }

    public void setMyStatementSqlMap(Map<String, MyStatementSql> myStatementSqlMap) {
        this.myStatementSqlMap = myStatementSqlMap;
    }
}
