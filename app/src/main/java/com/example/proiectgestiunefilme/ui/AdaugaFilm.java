package com.example.proiectgestiunefilme.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiectgestiunefilme.Film;
import com.example.proiectgestiunefilme.ICallbackDB;
import com.example.proiectgestiunefilme.R;
import com.example.proiectgestiunefilme.Singletone;
import com.example.proiectgestiunefilme.Utilizator;


public class AdaugaFilm extends Fragment {
    private Utilizator utilizator;
    private EditText et_denumire_film;
    private EditText et_an_aparitie;
    private EditText et_rating;
    private Button btn_adauga_film;
    private CheckBox cb_vizionat;
    private  CheckBox cb_preferat;
    Activity activity = getActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adauga_film, container, false);
        initViews(view);
        handleButon();
        return view;
    }
    public AdaugaFilm(Utilizator utilizator){
        this.utilizator=utilizator;
    }
    private void initViews(View view){
        et_denumire_film = view.findViewById(R.id.et_numefilm);
        et_an_aparitie = view.findViewById(R.id.et_anfilm);
        et_rating=view.findViewById(R.id.et_rating);
        btn_adauga_film=view.findViewById(R.id.buton_adauga_film);
        cb_vizionat=view.findViewById(R.id.cb_vazut);
        cb_preferat=view.findViewById(R.id.cb_preferat);
    }
    private boolean validate(){

        if(et_denumire_film.getText().toString().isEmpty()){
            Toast.makeText(activity,"Introduceti denumirea filmului",Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(et_an_aparitie.getText().toString().isEmpty()){
            Toast.makeText(activity,"Introduceti anul aparitiei filmului",Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(et_rating.getText().toString().isEmpty()){
            Toast.makeText(activity,"Introduceti rating-ul filmului",Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }
    private void handleButon(){
        btn_adauga_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String denumire = et_denumire_film.getText().toString();
                    int an = Integer.parseInt(et_an_aparitie.getText().toString());
                    double rating = Double.parseDouble(et_rating.getText().toString());
                    int id = utilizator.getUtilizatorId();
                    boolean vazut;
                    if(cb_vizionat.isChecked()){
                        vazut=true;
                    }
                    else{
                        vazut=false;
                    }
                    boolean preferat;
                    if(cb_preferat.isChecked()){
                        preferat=true;
                    }
                    else{
                        preferat=false;
                    }
                    //adaugare film in baza de date
                    Film film = new Film(denumire,an,rating,id,vazut,preferat,0);
                    //Singletone.getInstance(activity).FilmDAO().insertFilm(film);
                    //varianta asincrona
                    Singletone.getInstance(activity).insertFilm(film, new ICallbackDB() {
                        @Override
                        public void onSuccess() {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v("film","S-a daugat un nou film");
                                }
                            });
                        }

                        @Override
                        public void onFailure(Throwable error) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v("film",error.getLocalizedMessage());
                                }
                            });
                        }
                    });
                   // Toast.makeText(activity,"Filmul a fost adaugat",Toast.LENGTH_SHORT).show();
                    //golim campurile dupa ce filmul a fost adaugat
                    et_denumire_film.getText().clear();
                    et_an_aparitie.getText().clear();
                    et_rating.getText().clear();
                    cb_vizionat.setChecked(false);
                    cb_preferat.setChecked(false);
                }
            }
        });
    }
}