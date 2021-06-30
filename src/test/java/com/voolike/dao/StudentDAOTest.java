package com.voolike.dao;

import com.voolike.pojo.Student;
import com.voolike.utils.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOTest {

    @org.junit.Test
    public void insertStudent(){
            SqlSession sqlSession = MyBatisUtil.getSqlSession();
//            1.当我么获取到sqlsession时，默认开启了一次事务

        try {
//            通过会话获取DAO对象
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
//            测试studentDAO中的方法
            Student student = new Student(0, "10006", "赵六", "女", 14);
            int i = studentDAO.insertStudent(student);
            System.out.println(student);
//            操作完成之后需要手动提交
            sqlSession.commit();//手动提交
            System.out.println(i);
        }catch (Exception e){
//            当操作出现异常，调用rollback进行回滚
            sqlSession.rollback();
        }

    }

    @Test
    public void testDeleteStudent() {

        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            SqlSessionFactory factory = builder.build(is);

//        sqlSession表示Mybatis与数据库之间的会话，通过工厂模式获取
            SqlSession sqlSession = factory.openSession();//SqlSession sqlSession = factory.openSession(false);默认不提交

//        通过sqlsession对象调用getmapper方法获取DAO层接口对象
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            int i = studentDAO.deleteStudent("10001");
            sqlSession.commit();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateStudent(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
            int i = studentDAO.updateStudent(new Student(0, "10001", "赵四", "女", 18));
            sqlSession.commit();
            assertEquals(1,i);
    }

    @Test
    public void testListStudents(){
        StudentDAO studentDAO = MyBatisUtil.getMapper(StudentDAO.class);
        List<Student> list = studentDAO.listStudent();
//            assertNotNull(list);
            for (Student stu:list) {
                System.out.println(stu);
            }
    }

    @Test
    public void testQueryStudent(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            Student student = studentDAO.queryStudent("10001");
            System.out.println(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListStudentByPage(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
            List<Student> students = studentDAO.listStudentByPage(0, 2);
            for (Student stu:students) {
                System.out.println(stu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCount(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            int count = studentDAO.getCount();
            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}