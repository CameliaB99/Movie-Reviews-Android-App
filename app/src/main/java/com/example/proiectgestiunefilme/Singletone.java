package com.example.proiectgestiunefilme;

import android.content.Context;

import androidx.room.Room;

public class Singletone {
    private static Singletone instance;
    private DB database;
    private Singletone(Context context) {
        database = Room.databaseBuilder(context, DB.class, "tabel_actori")
                .allowMainThreadQueries()
                .build();
    }

    public static Singletone getInstance(Context context) {
        if (instance == null) {
            instance = new Singletone(context);
        }
        return instance;
    }


    public FilmDAO FilmDAO(){
        return database.filmDAO();
    }


    public UtilizatorDAO UtilizatorDAO(){
        return database.utilizatorDAO();
    }
        public void insertUtilizator(Utilizator utilizator, ICallbackDB iCallbackDB){
        new Thread(new Runnable() {
            @Override
            public void run() {
                UtilizatorDAO().insertUtilizator(utilizator);
                iCallbackDB.onSuccess();
            }
        }).start();
        }
    public void updateFilm(Film film, ICallbackDB iCallbackDB){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FilmDAO().updateFilm(film);
                iCallbackDB.onSuccess();
            }
        }).start();
    }

    public void stergeUtilizator(Utilizator utilizator, ICallbackDB iCallbackDB){
        new Thread(new Runnable() {
            @Override
            public void run() {
                UtilizatorDAO().stergeUtilizator(utilizator);
                iCallbackDB.onSuccess();
            }
        }).start();
    }
    public void insertFilm(Film film, ICallbackDB iCallbackDB){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FilmDAO().insertFilm(film);
                iCallbackDB.onSuccess();
            }
        }).start();
    }
    

}
