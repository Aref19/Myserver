package com.example.myserver.Save;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


@androidx.room.Database(entities = {DataBestellung.class}, version = 4)
public abstract class Database extends RoomDatabase {
  private  static Database INSTANCE;
  public abstract   DaoData daoData();

    public static synchronized Database getInstance(Context contex) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(contex.getApplicationContext(), Database.class, "email-database")
                    .fallbackToDestructiveMigration() //Strg + Q
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }


}
