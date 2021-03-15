package com.example.pawsy;

public class Association {

    private String fullName;
    private String country;
    private String city;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String description;
    private String linkSite;

    public Association(){

    }

    public Association(String fullName, String country, String city, String username, String password, String email, String phoneNumber, String description, String linkSite) {
        this.fullName = fullName;
        this.country = country;
        this.city = city;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.linkSite = linkSite;
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

    public String getLinkSite() {
        return linkSite;
    }

    public void setLinkSite(String linkSite) {
        this.linkSite = linkSite;
    }

    public String getAccountType() {
        return "association";
    }

    @Override
    public String toString() {
        return "Name: " + fullName + '\n' +
                "Country: " + country + '\n' +
                "City: " + city + '\n' +
                "Description: " + description + '\n';
    }
}
