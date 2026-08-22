package org.example.doyourself;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentTest {

    @Test
    void createsActiveStudentWithValidData() {
        Student student = new Student(1, " Alice ", "alice@example.com");

        assertEquals(1, student.getId());
        assertEquals("Alice", student.getName());
        assertEquals("alice@example.com", student.getEmail());
        assertEquals(StudentStatus.ACTIVE, student.getStatus());
    }

    @Test
    void rejectsInvalidConstructorArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student(0, "Alice", "alice@example.com"));
        assertThrows(IllegalArgumentException.class,
                () -> new Student(1, " ", "alice@example.com"));
        assertThrows(IllegalArgumentException.class,
                () -> new Student(1, "Alice", "invalid-email"));
    }

    @Test
    void changesEmailOnlyWhenItIsValid() {
        Student student = new Student(1, "Alice", "alice@example.com");

        student.changeEmail("new@example.com");

        assertEquals("new@example.com", student.getEmail());
        assertThrows(IllegalArgumentException.class, () -> student.changeEmail(null));
        assertEquals("new@example.com", student.getEmail());
    }

    @Test
    void graduatesActiveStudentOnlyOnce() {
        Student student = new Student(1, "Alice", "alice@example.com");

        student.graduate();

        assertEquals(StudentStatus.GRADUATED, student.getStatus());
        assertThrows(IllegalStateException.class, student::graduate);
    }
}
