package org.example.doyourself;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RolePolymorphismTest {

    @Test
    void studentRoleDispatchesThroughPersonReference() {
        Person person = new Student(1, "Alice", "alice@example.com");

        assertEquals("STUDENT", person.getRole());
    }

    @Test
    void instructorRoleDispatchesThroughPersonReference() {
        Person person = new Instructor(2, "Grace", "grace@example.com");

        assertEquals("INSTRUCTOR", person.getRole());
    }

    @Test
    void mixedPersonCollectionUsesRuntimeImplementations() {
        List<Person> people = List.of(
                new Student(1, "Alice", "alice@example.com"),
                new Instructor(2, "Grace", "grace@example.com")
        );

        List<String> roles = people.stream()
                .map(Person::getRole)
                .toList();

        assertEquals(List.of("STUDENT", "INSTRUCTOR"), roles);
    }
}
