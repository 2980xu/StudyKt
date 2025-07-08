package com.example.myapplication.aj.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StudentRoom.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "my_db";
    private static MyDataBase dataBase;

    public static synchronized MyDataBase getDataBase(Context context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(context.getApplicationContext(), MyDataBase.class, DATABASE_NAME).build();
        }
        return dataBase;
    }

    public abstract StudentDao studentDao();

}
