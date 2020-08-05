package com.example.myserver.Save;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myserver.Forderung;

import java.util.Date;

@Entity(tableName = "bestellungen")
public class DataBestellung {
    @PrimaryKey(autoGenerate = true)
    private int idd;
    private String id;
    private  String namePersEmail;
    private String name;
    private  String strasse;
    private String date;
    private String plz;

    public DataBestellung( String id, String namePersEmail, String name, String strasse, String hausnummer,String plz){
        this.namePersEmail=namePersEmail;
        this.name=name;
        this.strasse=strasse;
        this.date=hausnummer;
        this.plz=plz;
        this.id=id;
    }
    public DataBestellung(){

    }

    public void setStrasse(String anzahl) {
        this.strasse = anzahl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getStrasse() {
        return strasse;
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

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getPlz() {
        return plz;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public int getIdd() {
        return idd;
    }
}
