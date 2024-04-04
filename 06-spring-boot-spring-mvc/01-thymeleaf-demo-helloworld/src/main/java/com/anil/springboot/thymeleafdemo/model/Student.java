package com.anil.springboot.thymeleafdemo.model;

import java.util.Collections;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String favoriteLanguage;
    private List<String> favoriteSystems;

    public Student(){}

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getCountry() { return country; }
    public String getFavoriteLanguage() { return favoriteLanguage; }
    public List<String> getFavoriteSystems() { return favoriteSystems; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCountry(String country) { this.country = country;}
    public void setFavoriteLanguage(String favoriteLanguage) { this.favoriteLanguage = favoriteLanguage; }
    public void setFavoriteSystems(List<String> favoriteSystems) { this.favoriteSystems = favoriteSystems; }
}
