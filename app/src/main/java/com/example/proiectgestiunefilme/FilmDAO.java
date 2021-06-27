package com.example.proiectgestiunefilme;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FilmDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    void insertFilm(Film film);
    @Delete
    void deleteFilm(Film film);
    @Update
    void updateFilm(Film film);
    @Query("SELECT * FROM filme WHERE utilizatorId=:utilizatorId")
    List<Film> getAllFilme(int utilizatorId);
    @Query("SELECT * from filme WHERE denumireFilm=:denumire")
    Film getByDenumire(String denumire);
}
