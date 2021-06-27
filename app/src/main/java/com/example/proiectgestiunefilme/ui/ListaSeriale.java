package com.example.proiectgestiunefilme.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.proiectgestiunefilme.AdaptorSeriale;
import com.example.proiectgestiunefilme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListaSeriale extends Fragment {
private Activity activity;
private ListView lv_seriale;
private AdaptorSeriale adaptorSeriale;
    public ListaSeriale() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preluareSerialeFirebase();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_seriale, container, false);
        activity = getActivity();
        lv_seriale=view.findViewById(R.id.lv_seriale);

        return view;
    }
    public void preluareSerialeFirebase(){

        //preia instanta de firebase care e legata de proiect
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://proiectfilmeseriale-default-rtdb.firebaseio.com");
        //face o referinta pentru nodul care incepe cu ce am creat
        DatabaseReference referinta = firebaseDatabase.getReference("seriale");
        referinta.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 List<String> lista_seriale = (List<String>) dataSnapshot.getValue();
                //HashMap<Integer,String> lista_seriale = (HashMap<Integer, String>) dataSnapshot.getValue();
                Log.v("serial",lista_seriale.toString());
                adaptorSeriale= new AdaptorSeriale(lista_seriale,activity);
                lv_seriale.setAdapter(adaptorSeriale);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}