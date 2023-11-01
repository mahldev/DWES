package org.iesbelen.entitys;

import jakarta.servlet.http.HttpServletRequest;
import org.iesbelen.utils.CreationPersonResult;
import org.iesbelen.utils.ValidationResult;
import org.iesbelen.utils.ValidationError;

import java.util.HashSet;
import java.util.Set;

public class Person {

    private String name;
    private String surnames;
    private Integer age;
    private Integer weight;
    private String gender;
    private String maritalStatus;
    private Set<String> hobbies;


    public Person() {
        this.name = "";
        this.surnames = "";
        this.age = 0;
        this.weight = 0;
        this.gender = "";
        this.maritalStatus = "";
        this.hobbies = new HashSet<>();
    }

    public Person(String name, String surnames, Integer age,
                  Integer weight, String gender, String maritalStatus,
                  Set<String> hobbies) {
        this.name = name;
        this.surnames = surnames;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.hobbies = hobbies;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnnames() {
        return surnames;
    }

    public void setSurnnames(String surnnames) {
        this.surnames = surnnames;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<String> hobbies) {
        this.hobbies = hobbies;
    }

    private static String getParameterOrDefault(HttpServletRequest req, String paramName) {
        String value = req.getParameter(paramName);
        return value != null ? value : "";
    }

    private static Integer getIntegerParameterOrDefault(HttpServletRequest req, String paramName) {
        String value = req.getParameter(paramName);
        return value != null && !value.isEmpty() ? Integer.parseInt(value) : 0;
    }

    private static Set<String> getParameterValuesOrDefault(HttpServletRequest req, String paramName, Set<String> defaultValue) {
        String[] values = req.getParameterValues(paramName);
        return values != null ? Set.of(values) : defaultValue;
    }

    public static CreationPersonResult createPersonResultFromRequest(HttpServletRequest req) {
        final String name = getParameterOrDefault(req, "name");
        final String surnames = getParameterOrDefault(req, "surnames");
        final Integer age = getIntegerParameterOrDefault(req, "age");
        final Integer weight = getIntegerParameterOrDefault(req, "weight");
        final String gender = getParameterOrDefault(req, "gender");
        final String maritalStatus = getParameterOrDefault(req, "marital");
        final Set<String> hobbies = getParameterValuesOrDefault(req, "hobbies", new HashSet<>());

        final ValidationResult validateResult = validate(
                name, surnames, age, weight, gender, maritalStatus, hobbies);

        return new CreationPersonResult(
                new Person(name, surnames, age, weight, gender, maritalStatus, hobbies),
                validateResult);
    }

    public static ValidationResult validate(String name,
                                            String surnames,
                                            Integer age,
                                            Integer weight,
                                            String gender,
                                            String maritalStatus,
                                            Set<String> hobbies) {

        final var validateResult = new ValidationResult();

        if (name == null || name.isEmpty()) {
            validateResult.addError("name", ValidationError.NAME_REQUIRED);
        }

        if (surnames == null || surnames.isEmpty()) {
            validateResult.addError("surnames", ValidationError.SURNAMES_REQUIRED);
        }

        if (age == null || age < 0) {
            validateResult.addError("age", ValidationError.INVALID_AGE);
        }

        if (weight == null || weight < 0) {
            validateResult.addError("weight", ValidationError.INVALID_WEIGHT);
        }

        if ((!"Male".equals(gender) && !"Female".equals(gender))) {
            validateResult.addError("gender", ValidationError.INVALID_GENDER);
        }

        if ((!"Single".equals(maritalStatus)
                && !"Married".equals(maritalStatus)
                && !"Other".equals(maritalStatus))) {
            validateResult.addError("maritalStatus", ValidationError.INVALID_MARITAL_STATUS);
        }

        if (hobbies == null || hobbies.isEmpty()) {
            validateResult.addError("hobbies ", ValidationError.NO_HOBBIES_SELECTED);
        }

        return validateResult;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s - Apellidos: %s - Edad: %d " +
                        "- Peso: %d - Sexo: %s - Estado Civil: %s - Aficiones: %s",
                name,
                surnames,
                age,
                weight,
                gender,
                maritalStatus,
                hobbies.toString()
        );
    }
}
