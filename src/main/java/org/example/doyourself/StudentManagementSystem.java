package org.example.doyourself;

import java.util.List;

public class StudentManagementSystem {
    public static void main(String[] args) {
        Student student = new Student(1, "John Doe", "john.doe@example.com");
        Instructor instructor = new Instructor(2, "Grace Hopper", "grace@example.com");
        Course course = new Course("CS101", "Introduction to Computer Science", 3);
        student.enroll(course);

        student.displayCourses();

        List<Person> people = List.of(student, instructor);
        for (Person person : people) {
            System.out.println(person.getName() + ": " + person.getRole());
        }
    }
}
