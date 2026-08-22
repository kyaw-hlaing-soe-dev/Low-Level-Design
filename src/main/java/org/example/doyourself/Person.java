package org.example.doyourself;

public abstract class Person {

    private final int id;
    private final String name;
    private String email;

    protected Person(int id, String name, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        validateEmail(email);

        this.id = id;
        this.name = name.trim();
        this.email = email;
    }

    public void changeEmail(String newEmail) {
        validateEmail(newEmail);
        this.email = newEmail;
    }


    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be blank");
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0 || atIndex == email.length() - 1) {
            throw new IllegalArgumentException("Email must contain a valid @ separator");
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

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Person person = (Person) object;
        return id == person.id;
    }

    @Override
    public final int hashCode() {
        return Integer.hashCode(id);
    }
}
