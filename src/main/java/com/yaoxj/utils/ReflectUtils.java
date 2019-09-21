package com.yaoxj.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/7.
 */
public class ReflectUtils {
    /**

     * 将一个map集合封装成为bean对象


     *
     * @param param

     * @param clazz

     * @return

     */

    public static <T> T MapToBean(Map<String, Object> param, Class<?> clazz) {
        Object value = null;
        Class[] paramTypes = new Class[1];
        Object obj = null;
        try {
            obj = clazz.newInstance();
            // 获取类的属性
            Field[] declaredFields = clazz.getDeclaredFields();
            // 获取父类或接口的公有属性
            Field[] superFields = clazz.getSuperclass().getFields();
            List<Field[]> list = new ArrayList<Field[]>();
            if (declaredFields != null) {
                list.add(declaredFields);
            }
            if (superFields != null) {
                list.add(superFields);
            }

            for (Field[] fields : list) {
                for (Field field : fields) {
                    String fieldName = field.getName();
                    // 获取属性对应的值ֵ
                    value = param.get(fieldName);
                    // 把值设置进入对象属性中 这里可能是有属性但是没有相应的set方法，所以要做异常处理
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
                        Method method = pd.getWriteMethod();
                        method.invoke(obj, new Object[] { value });
                    } catch (Exception e1) {
                    }
                }
            }
        } catch (Exception e1) {
        }
        return (T) obj;
    }

    /**

     * 获取类的所有属性，包括父类和接口

     * @param clazz

     * @return

     */

    public static List<Field[]> getBeanFields(Class<?> clazz) {
        List<Field[]> list = new ArrayList<Field[]>();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] superFields = clazz.getSuperclass().getFields();
        if (declaredFields != null) {
            list.add(declaredFields);
        }
        if (superFields != null) {
            list.add(superFields);
        }
        return list;
    }
    /**

     * 从结果集中获取出值

     * @param fieldName

     * @param rs

     * @return

     */

    public static Object getFieldValue(String fieldName, ResultSet rs) {
        Object value = null;
        try {
            //捕获值不存在的异常
            value = rs.getObject(fieldName);
            return value;
        } catch (SQLException e) {
            //oracle数据库的列都是大写，所以才查找一次
            fieldName = fieldName.toLowerCase();
            try {
                value = rs.getObject(fieldName);
                return value;
            } catch (SQLException e1) {
                //结果集中没有对应的值，返回为空
                return null;
            }
        }
    }


    /**

     * 把结果集封装成为bean对象

     * @param rs

     * @param clazz

     * @return

     */

    public static <T> T RSToBean(ResultSet rs, Class<?> clazz) {

        Object obj = null;

        List<Field[]> list = getBeanFields(clazz);

        try {

            while (rs.next()) {

                obj = clazz.newInstance();
                for (Field[] fields : list) {

                    for (Field field : fields) {

                        String fieldName = field.getName();

                        // String fieldName = field.getName();ֵ

                        Object value = getFieldValue(fieldName, rs);

                        PropertyDescriptor pd = new PropertyDescriptor(

                                fieldName, clazz);

                        Method method = pd.getWriteMethod();

                        method.invoke(obj, new Object[] { value });

                    }

                }

            }

        } catch (Exception e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
        return (T) obj;

    }


    /**

     * 把结果集封装成为List


     *
     * @param rs

     * @param clazz

     * @return

     */

    public static <T> List<T> RsToList(ResultSet rs, Class<?> clazz) {

        ArrayList<T> objList = new ArrayList<T>();

        // 获取所有的属性

        List<Field[]> list = getBeanFields(clazz);

        try {

            while (rs.next()) {

                // 定义临时变量

                Object tempObeject = clazz.newInstance();



                // 添加到属性中

                for (Field[] fields : list) {

                    for (Field field : fields) {

                        String fieldName = field.getName();

                        // 获取属性值ֵ

                        Object value = getFieldValue(fieldName, rs);

                        PropertyDescriptor pd = new PropertyDescriptor(

                                fieldName, clazz);

                        Method method = pd.getWriteMethod();

                        method.invoke(tempObeject, new Object[] { value });

                    }

                }

                objList.add((T) tempObeject);

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return objList;

    }
}
