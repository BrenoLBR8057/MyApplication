package com.example.supermarket.ui.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.supermarket.ui.dao.SupermarketDAO;
import com.example.supermarket.ui.model.Supermarket;

@Database(entities = {Supermarket.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract SupermarketDAO supermarketDAO();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "supermarket.db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
