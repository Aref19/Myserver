package com.example.myserver.Save1;

public class Brobe {
    private String image;
    private  String anzahl;
    private String name;
    private String id;

    public  Brobe(String image,String anzahl,String name,String id){
        this.anzahl=anzahl;
        this.image=image;
        this.name=name;
        this.id=id;
    }

    public Brobe(){}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAnzahl(String anzahl) {
        this.anzahl = anzahl;
    }

    public String getAnzahl() {
        return anzahl;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
