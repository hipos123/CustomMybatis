package com.yaoxj.factory;

import com.yaoxj.configuration.MyConfiguration;
import com.yaoxj.executor.MyExecutor;

/**
 * Created by Administrator on 2019/9/6.
 */
public class MySqlSessionFactory {
    private MyConfiguration myConfiguration;
    public  MySqlSessionFactory(MyConfiguration myConfiguration){
        this.myConfiguration=myConfiguration;
    }

    public MySqlSession openSession() {
        //其实在这边就必须做好数据库连接的初始化操作了，因为打开一个会话的情况下，就相当于直接打开一个连接了
        MyExecutor myExecutor=new MyExecutor(myConfiguration);
        return new MySqlSession(myConfiguration,myExecutor);
    }
}
