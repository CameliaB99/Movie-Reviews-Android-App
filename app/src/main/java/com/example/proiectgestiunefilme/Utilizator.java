package com.example.proiectgestiunefilme;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
@Entity(tableName="utilizatori")
public class Utilizator {
    @PrimaryKey(autoGenerate = true)
    private int utilizatorId;
    private String denumireUtilizator;
    private String parola;
    @TypeConverters(ConvertorData.class)
    private LocalDate dataNastere;

    public Utilizator(String denumireUtilizator, String parola, LocalDate dataNastere) {
        this.denumireUtilizator = denumireUtilizator;
        this.parola = parola;
        this.dataNastere = dataNastere;
    }

    public int getUtilizatorId() {
        return utilizatorId;
    }

    public void setUtilizatorId(int utilizatorId) {
        this.utilizatorId = utilizatorId;
    }

    public String getDenumireUtilizator() {
        return denumireUtilizator;
    }

    public void setDenumireUtilizator(String denumireUtilizator) {
        this.denumireUtilizator = denumireUtilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public LocalDate getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(LocalDate dataNastere) {
        this.dataNastere = dataNastere;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "utilizatorId=" + utilizatorId +
                ", denumireUtilizator='" + denumireUtilizator + '\'' +
                ", parola='" + parola + '\'' +
                ", dataNastere=" + dataNastere +
                '}';
    }
}
