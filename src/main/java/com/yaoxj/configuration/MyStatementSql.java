package com.yaoxj.configuration;

/**
 * Created by Administrator on 2019/9/6.
 */
public class MyStatementSql {
    /** mapper文件的namespace */
    private String namespace;

    /** sql的id属性 */
    private String sqlId;

    /** sql语句，对应源码的sqlSource */
    private String sql;

    /** 返回类型 */
    private String resultType;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    private String paramType;
}
