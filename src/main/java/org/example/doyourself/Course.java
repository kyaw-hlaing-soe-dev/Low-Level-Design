package org.example.doyourself;


import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class Course {

    private String courseCode;
    private String title;
    private int credit;
    private List<Student> students = new ArrayList<>();

    public Course(String courseCode, String title, int credit) {
        this.courseCode = courseCode;
        this.title = title;
        this.credit = credit;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (students.contains(student)) return;
        students.add(student);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }
}
