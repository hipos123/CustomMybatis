package com.yaoxj.executor;

import com.yaoxj.configuration.MyEnviroment;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2019/9/6.
 */
public class MyDataSource implements MyDataSourceInface{
    private MyEnviroment myEnviroment;

    private Connection conn;
    private List<Connection> pools;
    private static final int POOL_SIZE=20;
    private static MyDataSource instance;

    private  MyDataSource(MyEnviroment myEnviroment) {
        this.myEnviroment=myEnviroment;
        pools=new ArrayList<Connection>(POOL_SIZE);//初始化20个连接池
        this.createConnection();
    }

    public  static MyDataSource getInsrance(MyEnviroment myEnviroment){
        if(instance==null){
            instance=new MyDataSource(myEnviroment);
        }
        return instance;
    }

    //创建一个简单的数据库连接池。
    private void createConnection() {
        for (int i=0;i<POOL_SIZE;i++){
            try {
                Class.forName(myEnviroment.getDriver());
                conn= DriverManager.getConnection(myEnviroment.getUrl(),myEnviroment.getUsername(),myEnviroment.getPassword());
                pools.add(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        return pools.get(0);
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }
}
