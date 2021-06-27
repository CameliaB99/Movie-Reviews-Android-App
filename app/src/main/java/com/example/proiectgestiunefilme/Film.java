package com.example.proiectgestiunefilme;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(tableName="filme",foreignKeys ={ @ForeignKey(entity=Utilizator.class,parentColumns="utilizatorId", childColumns = "utilizatorId", onDelete = ForeignKey.CASCADE )})
public class Film {
    @PrimaryKey(autoGenerate = true)
    private int filmId;
    private String denumireFilm;
    private int anAparitie;
    private double ratingImdb;
    private int utilizatorId;
    private boolean vazut;
    private boolean preferat;
    private double ratingPersonal;

    public Film(String denumireFilm, int anAparitie, double ratingImdb, int utilizatorId, boolean vazut, boolean preferat, double ratingPersonal) {
        this.denumireFilm = denumireFilm;
        this.anAparitie = anAparitie;
        this.ratingImdb = ratingImdb;
        this.utilizatorId = utilizatorId;
        this.vazut = vazut;
        this.preferat = preferat;
        this.ratingPersonal = ratingPersonal;
    }

    public boolean isVazut() {
        return vazut;
    }

    public void setVazut(boolean vazut) {
        this.vazut = vazut;
    }

    public boolean isPreferat() {
        return preferat;
    }

    public void setPreferat(boolean preferat) {
        this.preferat = preferat;
    }

    public double getRatingPersonal() {
        return ratingPersonal;
    }

    public void setRatingPersonal(double ratingPersonal) {
        this.ratingPersonal = ratingPersonal;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", denumireFilm='" + denumireFilm + '\'' +
                ", anAparitie=" + anAparitie +
                ", ratingImdb=" + ratingImdb +
                ", utilizatorId=" + utilizatorId +
                ", vazut=" + vazut +
                ", preferat=" + preferat +
                ", ratingPersonal=" + ratingPersonal +
                '}';
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getDenumireFilm() {
        return denumireFilm;
    }

    public void setDenumireFilm(String denumireFilm) {
        this.denumireFilm = denumireFilm;
    }

    public int getAnAparitie() {
        return anAparitie;
    }

    public void setAnAparitie(int anAparitie) {
        this.anAparitie = anAparitie;
    }

    public double getRatingImdb() {
        return ratingImdb;
    }

    public void setRatingImdb(double ratingImdb) {
        this.ratingImdb = ratingImdb;
    }

    public int getUtilizatorId() {
        return utilizatorId;
    }

    public void setUtilizatorId(int utilizatorId) {
        this.utilizatorId = utilizatorId;
    }

}
