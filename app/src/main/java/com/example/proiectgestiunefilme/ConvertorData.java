package com.example.proiectgestiunefilme;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;

public class ConvertorData {
    @TypeConverter
    public static String fromDate(LocalDate value){
        return value.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate stringToDate(String value){
        return LocalDate.parse(value);
    }

}
