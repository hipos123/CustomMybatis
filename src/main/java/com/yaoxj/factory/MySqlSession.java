package com.yaoxj.factory;

import com.yaoxj.configuration.MyConfiguration;
import com.yaoxj.configuration.MyStatementSql;
import com.yaoxj.executor.MyExecutor;
import com.yaoxj.mapper.MyInvocationHandler;
import com.yaoxj.mapper.UserMapper;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by Administrator on 2019/9/6.
 */
public class MySqlSession {
    private MyConfiguration myConfiguration;
    private MyExecutor myExecutor;

    public MySqlSession(MyConfiguration myConfiguration, MyExecutor myExecutor) {
        this.myConfiguration = myConfiguration;
        this.myExecutor = myExecutor;
    }

    public MyConfiguration getMyConfiguration() {
        return myConfiguration;
    }

    public void setMyConfiguration(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    public MyExecutor getMyExecutor() {
        return myExecutor;
    }

    public void setMyExecutor(MyExecutor myExecutor) {
        this.myExecutor = myExecutor;
    }


    public <T> T getMapper(Class clazz) {
        MyInvocationHandler myInvocationHandler=new MyInvocationHandler(this);
       return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},myInvocationHandler);
    }


    public <T> T selectOne(String statementKey, Object[] args) {
        MyStatementSql myStatementSql=myConfiguration.getMyStatementSqlMap().get(statementKey);
//        List retList=myExecutor.query(myStatementSql,args);
        //为了简单起见，就查一个参数
        List retList=myExecutor.query(myStatementSql,args==null?null:args[0]);
        if(retList==null&&retList.size()>1){
            throw new RuntimeException("查询结果集太多了");
        }else{
            return (T) retList.get(0);
        }
    }
}
