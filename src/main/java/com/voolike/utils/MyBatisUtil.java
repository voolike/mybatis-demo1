package com.voolike.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    /*加载主配置文件*/
    private static SqlSessionFactory factory;
    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();

    static {
        //        加载mybatis配置文件
        try {
           InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        会话工厂
            factory = builder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory(){
        return factory;
    }


    private static SqlSession getSqlSession(boolean isAutoCommit){
        SqlSession sqlSession = local.get();
        if (sqlSession == null){
            //通过sqlsessionfactory调用opensession方法获取sqlsession时，可以设置参数为true表示自动提交
            sqlSession = factory.openSession(isAutoCommit);
            local.set(sqlSession);
        }
        return sqlSession;
    }

//    手动事务管理
    public static SqlSession getSqlSession(){
        return getSqlSession(false);
    }


//    自动事务管理
    public static <T extends Object>T getMapper(Class<T> c){
        SqlSession sqlSession = getSqlSession(true);
//        T dao = sqlSession.getMapper(c);
        return sqlSession.getMapper(c);

    }

}
