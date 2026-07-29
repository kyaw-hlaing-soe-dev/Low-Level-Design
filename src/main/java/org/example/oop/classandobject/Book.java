package org.example.oop.classandobject;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public boolean borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    public void displayInfo() {
        String status = isAvailable ? "Available" : "Borrowed";
        System.out.println(title + " by " + author + " (ISBN: " + isbn + ") - " + status);
    }
}

