package com.example.qiang.d_scrollview.myRxJava;

import java.util.ArrayList;
import java.util.List;

/**学生 栗子
 * Created by qiang on 2015/11/3.
 */
public class Student {

    public int id;
    public String name;
    List<Course> courses;


    public static final int STUDENT_NUM = 5;

    public static final int COURSE_BUM=5;

    @Override
    public String toString() {
        return "id="+id+" name="+name;
    }



    public static class Course{

        public int id;
        public String name;

        @Override
        public String toString() {
            return "id="+id+" name="+name;
        }
    }


    public static List<Student> newStudentList(){


        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        for (int i =0;i<COURSE_BUM ;i++){

            Course c  =  new Course();
            c.id = i;
            c.name = "科目"+i;

            courses.add(c);

            Student student = new Student();
            student.id = i;
            student.name = "学生"+ i;
            student.courses = courses.subList(0,i);

            students.add(student);
        }

         return students;





    }

}


