package com.example.myserver.Save1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myserver.Save.DataBestellung;
@Dao
public interface DaoData1 {
    @Insert
    void insert(WarenBestellung dataBestellung) ;
    @Query("delete from waren")
    void deltetable();
}
