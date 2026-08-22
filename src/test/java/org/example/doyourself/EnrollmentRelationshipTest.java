package org.example.doyourself;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnrollmentRelationshipTest {

    @Test
    void enrollmentUpdatesBothSides() {
        Student student = new Student(1, "Alice", "alice@example.com");
        Course course = new Course("CS101", "Computer Science", 3);

        student.enroll(course);

        assertEquals(1, student.getCourses().size());
        assertEquals(course, student.getCourses().getFirst());
        assertEquals(1, course.getStudents().size());
        assertEquals(student, course.getStudents().getFirst());
    }

    @Test
    void enrollmentRejectsNull() {
        Student student = new Student(1, "Alice", "alice@example.com");

        assertThrows(NullPointerException.class, () -> student.enroll(null));
    }

    @Test
    void equivalentCoursesAreEnrolledOnlyOnce() {
        Student student = new Student(1, "Alice", "alice@example.com");
        Course first = new Course("CS101", "Computer Science", 3);
        Course duplicate = new Course("CS101", "Different title", 4);

        student.enroll(first);
        student.enroll(duplicate);

        assertEquals(1, student.getCourses().size());
        assertEquals(1, first.getStudents().size());
        assertEquals(0, duplicate.getStudents().size());
    }

    @Test
    void returnedCollectionsCannotBeModified() {
        Student student = new Student(1, "Alice", "alice@example.com");
        Course course = new Course("CS101", "Computer Science", 3);
        student.enroll(course);

        assertThrows(UnsupportedOperationException.class,
                () -> student.getCourses().clear());
        assertThrows(UnsupportedOperationException.class,
                () -> course.getStudents().clear());
    }

    @Test
    void equivalentStudentsAreAddedToCourseOnlyOnce() {
        Student first = new Student(1, "Alice", "alice@example.com");
        Student duplicate = new Student(1, "Alice Again", "alice.again@example.com");
        Course course = new Course("CS101", "Computer Science", 3);

        first.enroll(course);
        duplicate.enroll(course);

        assertEquals(1, course.getStudents().size());
    }
}
