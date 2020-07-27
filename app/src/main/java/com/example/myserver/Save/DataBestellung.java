package com.example.myserver.Save;

import android.provider.ContactsContract;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myserver.Forderung;

import java.util.Date;

@Entity(tableName = "bestellungen")
public class DataBestellung {
    @PrimaryKey(autoGenerate = true)
    private int  id;
    private  String namePersEmail;
    private String name;
    private  String anzahl;
    private String date;

    public DataBestellung(String namePersEmail, String name, String anzahl, String date){
this.namePersEmail=namePersEmail;
        this.name=name;
        this.anzahl=anzahl;
        this.date=date;
    }

    public void setAnzahl(String anzahl) {
        this.anzahl = anzahl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getAnzahl() {
        return anzahl;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNamePersEmail() {
        return namePersEmail;
    }

    public void setNamePersEmail(String namePersEmail) {
        this.namePersEmail = namePersEmail;
    }
}
