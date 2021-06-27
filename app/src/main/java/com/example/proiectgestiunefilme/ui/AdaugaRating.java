package com.example.proiectgestiunefilme.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proiectgestiunefilme.Film;
import com.example.proiectgestiunefilme.ICallbackDB;
import com.example.proiectgestiunefilme.R;
import com.example.proiectgestiunefilme.SignInActivity;
import com.example.proiectgestiunefilme.Singletone;
import com.example.proiectgestiunefilme.Utilizator;

import java.util.ArrayList;
import java.util.List;


public class AdaugaRating extends Fragment {
    private Utilizator utilizator;
    private Activity activity;
    private Spinner spiner;
    private RatingBar ratingBar;
    private Button btn_rating;

//constructor ca sa adaug rating-ul introdus de utilizator
    public AdaugaRating(Utilizator utilizator) {
        //initilizare ca sa transmit utilizatorul intre fragmente
    this.utilizator=utilizator;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_adauga_rating, container, false);
        activity=getActivity();
        initViews(view);
        handleButonRating();
        return view;
    }
    public void initViews(View view){
        spiner=view.findViewById(R.id.spinner_rating);
        ratingBar=view.findViewById(R.id.ratingBar);

        btn_rating = view.findViewById(R.id.buton_rating);
        //preluam lista de filme din baza de date in functie de id -ul utilizatorului
        List<Film> lista_filme = Singletone.getInstance(activity).FilmDAO().getAllFilme(utilizator.getUtilizatorId());
        //lista de stringuri
        //arraylist are mai multe proprietati fata de list
        ArrayList<String> denumire_film = new ArrayList<>();
        //pentru fiecare obiect de tip film din lista de filme adaugam denumirea filmului ca sa poata fi selectata din spinner
        for(Film f:lista_filme){
            denumire_film.add(f.getDenumireFilm());
        }
        //adapteaza lista de denumiri la optiunile din spinner
        ArrayAdapter adapter = new ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item,denumire_film);
        //pt spinerul nostru sa foloseasca adapterul de mai sus
        spiner.setAdapter(adapter);
    }
    public void handleButonRating(){
        btn_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String denumire = spiner.getSelectedItem().toString();
                Film film = Singletone.getInstance(activity).FilmDAO().getByDenumire(denumire);
                /*final Film[] film = new Film[1];
                Singletone.getInstance(activity).getByDenumire(denumire, new ICallbackDB() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(Throwable error) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("utilizator",error.getLocalizedMessage());
                            }
                        });
                    }

                    @Override
                    public void onSuccessFilm(Film rezultat) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                film[0] = rezultat;
                            }
                        });
                    }
                });*/
                //avand in vedere ca rating imdb este pana la 10 si noi avem doar 5 stele, o stea valoreaza 2 puncte
                double rating= 2 * ratingBar.getRating();

                film.setRatingPersonal(rating);
                //Singletone.getInstance(activity).FilmDAO().updateFilm(film);
                //varianta asincron
                Singletone.getInstance(activity).updateFilm(film, new ICallbackDB() {
                    @Override
                    public void onSuccess() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("utilizator","Filmul a fost modficat");
                            }
                        });

                    }

                    @Override
                    public void onFailure(Throwable error) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("utilizator",error.getLocalizedMessage());
                            }
                        });
                    }


                });
                Toast.makeText(activity,"S-a facut update pe film",Toast.LENGTH_SHORT).show();
            }
        });
    }

}