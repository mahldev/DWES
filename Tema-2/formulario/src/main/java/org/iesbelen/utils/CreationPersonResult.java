package org.iesbelen.utils;

import org.iesbelen.entitys.Person;

public class CreationPersonResult {

    private final Person person;
    private final ValidationResult errors;

    public CreationPersonResult(Person person) {
        this.person = person;
        this.errors = new ValidationResult();
    }

    public CreationPersonResult(Person person, ValidationResult errors) {
        this.person = person;
        this.errors = errors;
    }

    public boolean isValid() {
        return errors.isValid();
    }
    public Person getPerson() {
        return person;
    }

    public ValidationResult getErrors() {
        return errors;
    }
}
