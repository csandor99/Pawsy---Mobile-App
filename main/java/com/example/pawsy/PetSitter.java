package com.example.pawsy;

public class PetSitter {

    private String fullName;
    private String country;
    private String city;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String description;
    private String price;

    public PetSitter(){

    }

    public PetSitter(String fullName, String country, String city, String username, String password, String email, String phoneNumber, String description,String price) {
        this.fullName = fullName;
        this.country = country;
        this.city = city;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.price = price;

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAccountType() {
        return "petsitter";
    }

    @Override
    public String toString() {
        return "Name: " + fullName + '\n' +
                "User: " + username + '\n' +
                "Phone number: " + phoneNumber + '\n' +
                "Price: " + price + '\n';
    }
}
