package org.example.oop.enums;

public class HttpStatusCodeCheck {
    public static void main(String[] args) {
        HttpStatusCode.OK.display();
        HttpStatusCode.NOT_FOUND.display();

        System.out.println("Is 200 success? " + HttpStatusCode.OK.isSuccess());
        System.out.println("Is 404 success? " + HttpStatusCode.NOT_FOUND.isSuccess());

        HttpStatusCode found = HttpStatusCode.fromCode(500);
        if (found != null) {
            System.out.print("Found by code 500: ");
            found.display();
        }
    }
}
