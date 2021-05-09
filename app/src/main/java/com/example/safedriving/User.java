package com.example.safedriving;

public class User {
    int _id;
    String accountid;
    String gender;
    double weight;

    public User() {
        // does mothing, empty constructor
    }

    public User(String i, String g, double w) {
        accountid = i;
        gender = g;
        weight = w;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getToken() {
        return accountid;
    }

    public String getGender() {
        return gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setToken(String t) {
        accountid = t;
    }

    public void setGender(String g) {
        gender = g;
    }

    public void setWeight(double w) {
        weight = w;
    }

    public String str() {
        return "USER DATA : ID = " + getToken() + "   W = " + getWeight() + "  AND G = " + getGender();
    }
}
