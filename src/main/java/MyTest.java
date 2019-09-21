import com.yaoxj.bean.User;
import com.yaoxj.factory.MySqlSession;
import com.yaoxj.factory.MySqlSessionFactory;
import com.yaoxj.factory.MySqlSessionFactoryBuilder;
import com.yaoxj.mapper.UserMapper;

import java.io.InputStream;

/**
 * Created by yaoxj on 2019/9/6.
 */
public class MyTest {

    public static void main(String[] args) {
        //第一步：读取mybatis-config.xml配置文件（里面主要是数据库环境信息，事务信息）
        InputStream inputStream=MyTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");

        //第二步：构建SqlsessionFactory，主要是解析mybatis-config.xml这个文件，还有解析mapper-xml文件。
        //这个2个文件全部放在一个配置文件里面。
        MySqlSessionFactory mySqlSessionFactory=new MySqlSessionFactoryBuilder().builder(inputStream);

        //第三步:打开session,这个session包含配置信息和一个执行器
        MySqlSession mySqlSession=mySqlSessionFactory.openSession();

        //第四步：获取mapper接口对象,根据传进去的对象，获取到这个对象的，这个是动态代理的做法。
        //这边不能做成静态代理，一个对象写一个方法，那么就不合适了
        UserMapper userMapper=mySqlSession.getMapper(UserMapper.class);

        //第五步，使用map对象获取信息
        User userinfo=userMapper.getUser("1");

        System.out.println(userinfo.getId()+":"+userinfo.getName());


    }
}
