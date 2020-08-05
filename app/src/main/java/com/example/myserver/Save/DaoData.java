package com.example.myserver.Save;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoData  {

    @Insert
    void insert(DataBestellung dataBestellung) ;
    @Query("select * from bestellungen where namePersEmail= :id ")
    List<DataBestellung> getsache(String id);
    @Query("insert into bestellungen (date) VALUES (:date)")
    void inserdate(String date);
    @Query("insert into bestellungen (id) VALUES (:id)")
    void inserid(String id);
    @Query("insert into bestellungen (name,strasse,date) values (:name,:strasse,:date)")
    void inserNmaeAnzahl(String name, String strasse, String date);
      @Query("select Max(id) from bestellungen ")
       int sele();
    @Query("delete from bestellungen")
    void deltetable();
    @Query("select * from bestellungen where namePersEmail= :id ")
    boolean check(String id);
    @Query("select * from bestellungen  ")
    boolean checktable();


}
