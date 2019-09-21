package com.yaoxj.mapper;

import com.yaoxj.factory.MySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/7.
 */
public class MyInvocationHandler implements InvocationHandler {

    private MySqlSession mySqlSession;

    public MyInvocationHandler(MySqlSession mySqlSession) {
        this.mySqlSession=mySqlSession;
    }

    //方法的回调都在这些操作，比如说查询，插入，更新操作都在这个方法中进行
    //需要传入一个参数，
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> clazz=method.getReturnType();
        //在statementMap中存放的是命名空间.id ,这边引发出来了一个小问题就是方法名称一定要和id的名称一致，否则就找不到这个key
        String statementKey=method.getDeclaringClass().getName()+"."+method.getName();

        //判断返回值是什么类型
        if(Collections.class.isAssignableFrom(clazz)){

        }else if(Map.class.isAssignableFrom(clazz)){//返回map

        }else{//返回对象
            return  mySqlSession.selectOne(statementKey,args);
        }
        return null;
    }
}
