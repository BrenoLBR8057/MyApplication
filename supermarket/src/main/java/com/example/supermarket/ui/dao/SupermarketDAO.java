package com.example.supermarket.ui.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.supermarket.ui.model.Supermarket;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface SupermarketDAO {
    @Insert
    void insert(Supermarket supermarket);
    @Delete
    void delete(Supermarket supermarket);
    @Update
    void update(Supermarket supermarket);
    @Query("SELECT * FROM Supermarket")
    List<Supermarket> getAll();
    @Query("SELECT * FROM Supermarket")
    List<Supermarket> getTotalFinal();
}
