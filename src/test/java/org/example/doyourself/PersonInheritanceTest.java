package org.example.doyourself;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonInheritanceTest {

    @Test
    void instructorInheritsCommonPersonData() {
        Instructor instructor = new Instructor(10, " Grace Hopper ", "grace@example.com");

        assertEquals(10, instructor.getId());
        assertEquals("Grace Hopper", instructor.getName());
        assertEquals("grace@example.com", instructor.getEmail());
    }

    @Test
    void instructorUsesPersonValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Instructor(0, "Grace", "grace@example.com"));
        assertThrows(IllegalArgumentException.class,
                () -> new Instructor(10, " ", "grace@example.com"));
        assertThrows(IllegalArgumentException.class,
                () -> new Instructor(10, "Grace", "invalid-email"));
    }

    @Test
    void instructorUsesControlledEmailChanges() {
        Instructor instructor = new Instructor(10, "Grace", "grace@example.com");

        instructor.changeEmail("new@example.com");

        assertEquals("new@example.com", instructor.getEmail());
        assertThrows(IllegalArgumentException.class, () -> instructor.changeEmail(null));
        assertEquals("new@example.com", instructor.getEmail());
    }

    @Test
    void identityUsesIdWithinEachConcretePersonType() {
        Student firstStudent = new Student(1, "Alice", "alice@example.com");
        Student sameStudent = new Student(1, "Alice Again", "alice.again@example.com");
        Instructor firstInstructor = new Instructor(1, "Grace", "grace@example.com");
        Instructor sameInstructor = new Instructor(1, "Grace Again", "grace.again@example.com");

        assertEquals(firstStudent, sameStudent);
        assertEquals(firstStudent.hashCode(), sameStudent.hashCode());
        assertEquals(firstInstructor, sameInstructor);
        assertEquals(firstInstructor.hashCode(), sameInstructor.hashCode());
        assertNotEquals(firstStudent, firstInstructor);
    }
}
