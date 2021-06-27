package com.example.proiectgestiunefilme.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.proiectgestiunefilme.AdaptorFilm;
import com.example.proiectgestiunefilme.Film;
import com.example.proiectgestiunefilme.R;
import com.example.proiectgestiunefilme.Singletone;
import com.example.proiectgestiunefilme.Utilizator;

import java.util.List;


public class ListaFilme extends Fragment {
private Activity activity;
private Utilizator utilizator;
private ListView listView;
private AdaptorFilm adaptorFilm;

    public ListaFilme(Utilizator utilizator) {
        this.utilizator=utilizator;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_lista_filme, container, false);
        activity=getActivity();
        initViews(view);
        return view;
    }
    private void initViews(View view){
        List<Film> lista_filme = Singletone.getInstance(activity).FilmDAO().getAllFilme(utilizator.getUtilizatorId());
        adaptorFilm=new AdaptorFilm(lista_filme,activity);
        listView=view.findViewById(R.id.lv_filme);
        listView.setAdapter(adaptorFilm);
    }
}