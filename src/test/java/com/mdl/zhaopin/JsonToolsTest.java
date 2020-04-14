package com.mdl.zhaopin;

import com.alibaba.fastjson.JSONArray;
import com.mdl.zhaopin.test.Student;
import com.mdl.zhaopin.utils.JsonTools;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月13日 14:30
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class JsonToolsTest {

    @Test
    public void obj2String() {
        Student student = new Student(1001, "xiaoming", "M", 80.2);
        String s = JsonTools.obj2String(student);
        System.out.println(s);
    }

    @Test
    public void obj2StringPretty() {
        Student student = new Student(1001, "xiaoming", "M", 80.2);
        String s = JsonTools.obj2StringPretty(student);
        System.out.println(s);
    }

    @Test
    public void string2Obj() {
        String s = "{\n" +
                "  \"id\" : 1001,\n" +
                "  \"name\" : \"xiaoming\",\n" +
                "  \"sex\" : \"M\",\n" +
                "  \"grade\" : 80.2\n" +
                "}";
        Student student = JsonTools.string2Obj(s, Student.class);
        System.out.println(student);
    }

    @Test
    public void string2ObjRef() {
        Student xiaoming = new Student(1001, "xiaoming", "M", 80.2);
        Student xiaobai = new Student(1002, "xiaobai", "F", 80.3);
        List<Student> studentList = new ArrayList<>();
        studentList.add(xiaoming);
        studentList.add(xiaobai);
        String studentListStr = JsonTools.obj2StringPretty(studentList);
        List<Student> studentListObj = JsonTools.string2ObjRef(studentListStr, new TypeReference<List<Student>>() {
        });
        System.out.println(studentListObj.toString());
    }

    @Test
    public void string2Collection() {
        Student xiaoming = new Student(1001, "xiaoming", "M", 80.2);
        Student xiaobai = new Student(1002, "xiaobai", "F", 80.3);
        List<Student> studentList = new ArrayList<>();
        studentList.add(xiaoming);
        studentList.add(xiaobai);
        String studentListStr = JsonTools.obj2StringPretty(studentList);
        //List<Student> studentListObj = JsonTools.string2ObjRef(studentListStr, new TypeReference<List<Student>>(){});
        // 依次传入集合以及集合中对象类型的class
        List<Student> studentListObj = JsonTools.string2Collection(studentListStr, List.class, Student.class);
        System.out.println(studentListObj.toString());
    }

    @Test
    public void getListByJSONArray() {
        //定义一个数组
        List<Student> students = new ArrayList<>();
        Student xiaoming = new Student(1001, "xiaoming", "M", 80.2);
        Student xiaohong = new Student(1002, "xiaohong", "M", 80.2);
        students.add(xiaoming);
        students.add(xiaohong);
        //将List转换为jsonArray
        JSONArray student = JsonTools.getJSONArrayByList(students);
        //将jsonArray转为List
        List<Student> studentList = JsonTools.getListByJSONArray(Student.class, student.toJSONString());
        System.out.println(studentList);
    }

    @Test
    public void getJSONArrayByList() {
        //定义一个数组
        List<Student> students = new ArrayList<>();
        Student xiaoming = new Student(1001, "xiaoming", "M", 80.2);
        Student xiaobai = new Student(1002, "xiaobai", "M", 80.2);
        students.add(xiaoming);
        students.add(xiaobai);
        //将List转换为jsonArray
        JSONArray student = JsonTools.getJSONArrayByList(students);
        System.out.println(student.toJSONString());
    }







}
