package com.example.myserver;

public class Ware {
    private String name;
    private String des;
    private String preis;
    private String foto;

    public  Ware(String name,String des,String foto ){
        this.des=des;
        this.foto=foto;

        this.name=name;
    }

    public String getDes() {
        return des;
    }

    public String getFoto() {
        return foto;
    }

    public String getName() {
        return name;
    }

    public String getPreis() {
        return preis;
    }
}
