package com.example.pawsy;

public class Animal {
    private String owner, name, gender, description, specialNeeds, microchip;
    private String age;

    public Animal(){

    }

    public Animal(String owner, String name, String gender, String description, String specialNeeds, String microchip, String age) {
        this.owner = owner;
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.specialNeeds = specialNeeds;
        this.microchip = microchip;
        this.age = age;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public String getMicrochip() {
        return microchip;
    }

    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n'
                + "Gender: " + gender + '\n'
                + "Age: " + age + '\n'
                + "Description: " + description + '\n'
                + "Special needs: " + specialNeeds + '\n'
                + "Microchip: " + microchip + '\n'
        ;
    }
}
