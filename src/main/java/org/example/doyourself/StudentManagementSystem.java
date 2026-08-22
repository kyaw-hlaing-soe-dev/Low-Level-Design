package org.example.doyourself;

public class StudentManagementSystem {
    public static void main(String[] args) {
        Student student = new Student(1, "John Doe", "john.doe@example.com");
        Course course = new Course("CS101", "Introduction to Computer Science", 3);
        student.enroll(course);

        student.displayCourses();
    }
}
