package modular.tool;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
    private static String config="spring=config.xml";
    static Reader reader;
    static{
        try {
            reader= Resources.getResourceAsReader(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
    //提供一个可以获取到session的方法

    public static SqlSession getSession() throws IOException{
        SqlSession session = factory.openSession();
        return session;
    }
}
