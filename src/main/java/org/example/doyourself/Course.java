package org.example.doyourself;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {

    private final String courseCode;
    private final String title;
    private final int credits;
    private final List<Student> students = new ArrayList<>();

    public Course(String courseCode, String title, int credits) {
        if (courseCode == null || courseCode.isBlank()) {
            throw new IllegalArgumentException("Course code must not be blank");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Course title must not be blank");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Course credits must be positive");
        }

        this.courseCode = courseCode.trim();
        this.title = title.trim();
        this.credits = credits;
    }

    void addStudent(Student student) {
        Objects.requireNonNull(student, "Student must not be null");
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public List<Student> getStudents() {
        return List.copyOf(students);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Course course)) {
            return false;
        }
        return courseCode.equals(course.courseCode);
    }

    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }
}
