package org.iesbelen.utils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private final Map<String, ValidationError> errors;

    public ValidationResult() {
        this.errors = new HashMap<>();
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void addError(String key, ValidationError value) {
        errors.put(key, value);
    }

    public Map<String, ValidationError> getErrors() {
        return errors;
    }
}
