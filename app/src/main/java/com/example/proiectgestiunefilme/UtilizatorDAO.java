package com.example.proiectgestiunefilme;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UtilizatorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUtilizator(Utilizator utilizator);
    @Query("SELECT * from utilizatori where denumireUtilizator=:numeUtilizator")
    Utilizator getByDenumire(String numeUtilizator);
    @Delete
    void stergeUtilizator(Utilizator utilizator);
    @Update
    void updateUtilizator(Utilizator utilizator);
}
