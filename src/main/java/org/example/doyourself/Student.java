package org.example.doyourself;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;
    private String name;
    private String email;
    private List<Course> courses = new ArrayList<>();
    private StudentStatus status;

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayCourses() {
        if (courses != null) {
            System.out.println("Courses for student " + name + ":");
            for (Course course : courses) {
                System.out.println(course.getTitle() + " (" + course.getCourseCode() + ")");
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
