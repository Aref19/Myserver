package com.example.myserver.Save1;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myserver.Save.DaoData;
import com.example.myserver.Save.DataBestellung;
import com.example.myserver.Save.Database;

@androidx.room.Database(entities = {WarenBestellung.class}, version = 1)
public abstract class Database1 extends RoomDatabase {
    private  static Database1 INSTANCE;
    public abstract DaoData1 daoData();

    public static synchronized Database1 getInstance(Context contex) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(contex.getApplicationContext(), Database1.class, "email-database")
                    .fallbackToDestructiveMigration() //Strg + Q
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
