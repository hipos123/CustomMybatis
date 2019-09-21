package com.yaoxj.executor;

import com.yaoxj.configuration.MyConfiguration;
import com.yaoxj.configuration.MyStatementSql;
import com.yaoxj.utils.ReflectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/9/6.
 */
public class MyExecutor {

    private MyDataSource myDataSource;
    public MyExecutor(MyConfiguration myConfiguration) {
        this.myDataSource = MyDataSource.getInsrance(myConfiguration.getMyEnviroment());
    }


    public <T> List<T> query(MyStatementSql myStatementSql, Object args) {
        List retList=new ArrayList();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=myDataSource.getConnection();
            //myStatementSql.getSql() 这个sql中的一些#{} 需要做替换，变成？才方便后续做设置
            preparedStatement=connection.prepareStatement(myStatementSql.getSql());
            if(args instanceof  Integer){
                preparedStatement.setInt(1,(Integer)args);
            }else if(args instanceof  Double){
                preparedStatement.setDouble(1,(Double)args);
            }else if(args instanceof String){
                preparedStatement.setString(1,(String)args);
            }
            resultSet=preparedStatement.executeQuery();
//            ReflectUtils.RsToList(resultSet,)
            retList=handlerResultSet(resultSet,myStatementSql.getResultType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retList;
    }

    //通过反射将数据查询出来的结果集映射到返回的对象里面
    public  <T> List<T>  handlerResultSet(ResultSet resultSet,String resultType){
        try {
            Class<T> clazz= (Class<T>) Class.forName(resultType);
            return ReflectUtils.RsToList(resultSet,clazz);

          /*  while(resultSet.next()){//循环结果集
                //通过反射获取实例对象
                Object entity=clazz.newInstance();
                //把从数据库获取到的对象映射到实例中
                //// TODO: 2019/9/7
                ReflectUtils.RSToBean(resultSet,clazz);
                //将结果集放到list中
                restList.add((T)entity);
            }*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
