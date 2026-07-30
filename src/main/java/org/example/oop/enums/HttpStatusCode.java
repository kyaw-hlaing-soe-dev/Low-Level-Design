package org.example.oop.enums;

public enum HttpStatusCode {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    HttpStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        if(code < 400) {
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println(code + " " + message);
    }

    public static HttpStatusCode fromCode(int code) {
        for (HttpStatusCode status : HttpStatusCode.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
}

