package org.iesbelen.utils;

public enum ValidationError {
    NAME_REQUIRED("Name is required"),
    SURNAMES_REQUIRED("Surnames are required"),
    INVALID_AGE("Invalid age"),
    INVALID_WEIGHT("Invalid weight"),
    INVALID_GENDER("Invalid gender"),
    INVALID_MARITAL_STATUS("Invalid marital status"),
    NO_HOBBIES_SELECTED("No hobbies selected");

    private final String message;

    private ValidationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
