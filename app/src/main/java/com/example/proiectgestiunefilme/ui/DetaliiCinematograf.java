package com.example.proiectgestiunefilme.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proiectgestiunefilme.IJson;
import com.example.proiectgestiunefilme.ManagerJson;
import com.example.proiectgestiunefilme.R;


public class DetaliiCinematograf extends Fragment {
private TextView tv_detalii;
private Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_detalii_cinematograf, container, false);
        activity=getActivity();
        tv_detalii=view.findViewById(R.id.tv_detalii);
        ManagerJson.getInstance().preluareDate(new IJson() {
            @Override
            public void onSuccess(String text) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //preluam text din json
                        tv_detalii.setText(text);
                    }
                });
            }

            @Override
            public void onFailure(Throwable error) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        return view;
    }
}