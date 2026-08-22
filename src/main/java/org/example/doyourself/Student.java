package org.example.doyourself;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends Person {

    private final List<Course> courses = new ArrayList<>();
    private StudentStatus status;


    public Student(int id, String name, String email) {
        super(id, name, email);
        this.status = StudentStatus.ACTIVE;
    }

    public void enroll(Course course) {
        Objects.requireNonNull(course, "Course must not be null");
        if (!courses.contains(course)) {
            courses.add(course);
            course.addStudent(this);
        }
    }

    public void displayCourses() {
        System.out.println("Courses for student " + getName() + ":");
        for (Course course : courses) {
            System.out.println(course.getTitle() + " (" + course.getCourseCode() + ")");
        }
    }

    public void graduate() {
        if (status != StudentStatus.ACTIVE) {
            throw new IllegalStateException("Only active students can graduate");
        }
        status = StudentStatus.GRADUATED;
    }


    public StudentStatus getStatus() {
        return status;
    }

    public List<Course> getCourses() {
        return List.copyOf(courses);
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }
}
