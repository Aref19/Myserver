package com.example.myserver.Save1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "waren")
public class WarenBestellung {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private  String date;
    private String name;
    private String anzahle;


    public WarenBestellung(){
    }
    public WarenBestellung(String date,String name,String anzahle){

        this.name=name;
        this.date=date;
        this.anzahle=anzahle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAnzahle(String anzahle) {
        this.anzahle = anzahle;
    }

    public String getName() {
        return name;
    }

    public String getAnzahle() {
        return anzahle;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
