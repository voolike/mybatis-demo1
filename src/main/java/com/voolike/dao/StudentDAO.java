package com.voolike.dao;

import com.voolike.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDAO
{
//    在Mybatis中进行操作
//    1.如果操作方法只有一个简单类型或者字符串类型参数，在Mapper配置中可以直接通过#{str}直接获取
//    2.如果操作方法有一个对象类型的参数，在Mapper配置中可以通过#{attrName}获取对象的指定属性值，（attrName必须是参数对象的属性）
//    3.如果操作方法有一个Map类型的参数，在Mapper配置中可直接通过#{key}获取key对应的value
//    4.如果操作方法有多个参数，



    public int insertStudent(Student student);
    public int deleteStudent(String stuNum);
    public int updateStudent(Student student);
    public List<Student> listStudent();
    public Student queryStudent(String stuNum);
    public List<Student> listStudentByPage(@Param("start") int start, @Param("pageSize") int pageSize);
    public int getCount();

}
