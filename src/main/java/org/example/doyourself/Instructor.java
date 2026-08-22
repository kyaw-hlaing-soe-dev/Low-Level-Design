package org.example.doyourself;

public class Instructor extends Person {

    public Instructor(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getRole() {
        return "INSTRUCTOR";
    }
}
