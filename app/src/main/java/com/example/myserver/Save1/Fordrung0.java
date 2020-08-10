package com.example.myserver.Save1;

public class Fordrung0 {
    private String image;
    private  int anzahl;
    private String name;
    private int  id;

    public Fordrung0(String image, int  anzahl, String name, int  id){
        this.anzahl=anzahl;
        this.image=image;
        this.name=name;
        this.id=id;
    }

    public Fordrung0(){}

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public long getAnzahl() {
        return anzahl;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
