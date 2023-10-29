package org.iesbelen.entitys;

import java.util.Arrays;

public class Person {

    private String name;
    private String surnnames;
    private Integer age;
    private Integer weight;
    private String gender;
    private String maritalStatus;
    private String[] hobbies;

    public Person(String name, String surnnames, Integer age,
                  Integer weight, String gender, String maritalStatus,
                  String[] hobbies) {
        this.name = name;
        this.surnnames = surnnames;
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
        return surnnames;
    }

    public void setSurnnames(String surnnames) {
        this.surnnames = surnnames;
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

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s - Apellidos: %s - Edad: %d " +
                        "- Peso: %d - Sexo: %s - Estado Civil: %s - Aficiones: %s",
                name,
                surnnames,
                age,
                weight,
                gender,
                maritalStatus,
                Arrays.toString(hobbies)
        );
    }
}
