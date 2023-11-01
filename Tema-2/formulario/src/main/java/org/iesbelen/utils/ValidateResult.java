package org.iesbelen.utils;

import java.util.HashMap;
import java.util.Map;

public class ValidateResult {

    private final Map<String, ValidationError> errors;

    public ValidateResult() {
        this.errors = new HashMap<>();
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void addResult(String key, ValidationError value) {
        errors.put(key, value);
    }

    public Map<String, ValidationError> getErrors() {
        return errors;
    }
}
