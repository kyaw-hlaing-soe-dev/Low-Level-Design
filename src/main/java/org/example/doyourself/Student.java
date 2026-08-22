package org.example.doyourself;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final int id;
    private final String name;
    private String email;
    private final List<Course> courses = new ArrayList<>();
    private StudentStatus status;

    public Student(int id, String name, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name must not be blank");
        }
        validateEmail(email);

        this.id = id;
        this.name = name.trim();
        this.email = email;
        this.status = StudentStatus.ACTIVE;
    }

    public void addCourse(Course course) {
        if (course == null || this.courses.contains(course) ) {
            return;
        }
        courses.add(course);
    }

    public void displayCourses() {
        System.out.println("Courses for student " + name + ":");
        for (Course course : courses) {
            System.out.println(course.getTitle() + " (" + course.getCourseCode() + ")");
        }
    }

    public void changeEmail(String newEmail) {
        validateEmail(newEmail);
        this.email = newEmail;
    }

    public void graduate() {
        if (status != StudentStatus.ACTIVE) {
            throw new IllegalStateException("Only active students can graduate");
        }
        status = StudentStatus.GRADUATED;
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Student email must not be blank");
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0 || atIndex == email.length() - 1) {
            throw new IllegalArgumentException("Student email must contain a valid @ separator");
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

    public StudentStatus getStatus() {
        return status;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
