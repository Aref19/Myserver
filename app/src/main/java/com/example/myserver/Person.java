package com.example.myserver;

public class Person {
    String name;
    String Email;
    String strasse;
    String haus;
    String plz;


    public String getStrasse() {
        return strasse;
    }

    public String getPlz() {
        return plz;
    }

    public String getHaus() {
        return haus;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setHaus(String haus) {
        this.haus = haus;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }
}
