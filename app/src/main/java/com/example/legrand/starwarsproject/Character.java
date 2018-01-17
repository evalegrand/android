package com.example.legrand.starwarsproject;

/**
 * Created by legrand on 16/01/2018.
 */

public class Character {

    private String name;
    private String height;
    private String birth_year;
    private String eye_color;
    private String gender;
    private String hair_color;
    private String mass;
    private String skin_color;
    private String homeworld;

    public Character(String name, String height, String mass, String hair_color, String skin_color, String eye_color, String birth_year, String gender, String homeworld){
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair_color = hair_color;
        this.skin_color = skin_color;
        this.eye_color = eye_color;
        this.birth_year = birth_year;
        this.gender = gender;
        this.homeworld = homeworld;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getGender() {
        return gender;
    }

    public String getHair_color() {
        return hair_color;
    }

    public String getMass() {
        return mass;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public String getHomeworld() {
        return homeworld;
    }
}
