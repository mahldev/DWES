package org.iesbelen.utils;

import org.iesbelen.entitys.Person;

public class CreationPersonResult {

    private final Person person;
    private final ValidateResult errors;

    public CreationPersonResult(Person person) {
        this.person = person;
        this.errors = new ValidateResult();
    }

    public CreationPersonResult(Person person, ValidateResult errors) {
        this.person = person;
        this.errors = errors;
    }

    public Person getPerson() {
        return person;
    }

    public ValidateResult getErrors() {
        return errors;
    }
}
