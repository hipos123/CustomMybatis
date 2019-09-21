package com.yaoxj.executor;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2019/9/6.
 */
public interface MyDataSourceInface extends DataSource {

    //default jdk新特性，默认方法，实现类可以不实现做个方法，需要实现直接重载就好
    @Override
    default Connection getConnection() throws SQLException {
        return null;
    }
    @Override
    default Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    default PrintWriter getLogWriter() throws SQLException {
        return null;
    }
    @Override
    default void setLogWriter(PrintWriter out) throws SQLException {

    }
    @Override
    default void setLoginTimeout(int seconds) throws SQLException {

    }
    @Override
    default int getLoginTimeout() throws SQLException {
        return 0;
    }
    @Override
    default Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
    @Override
    default <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }
    @Override
    default boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
