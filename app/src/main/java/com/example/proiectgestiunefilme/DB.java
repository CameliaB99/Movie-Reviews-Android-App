package com.example.proiectgestiunefilme;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Utilizator.class,Film.class},version = 1,exportSchema = false)
public abstract class DB extends RoomDatabase {
    abstract UtilizatorDAO utilizatorDAO();
    abstract  FilmDAO filmDAO();
}
