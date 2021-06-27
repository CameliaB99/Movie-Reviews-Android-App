package com.example.proiectgestiunefilme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LogInActivity extends AppCompatActivity {

    private EditText et_nume;
    private EditText et_parola;
    private Button btn_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initViews();
        handleButon();
    }
    public void initViews(){
        et_nume=findViewById(R.id.et_nume);
        et_parola=findViewById(R.id.et_parola);
        btn_signin=findViewById(R.id.buton_login);
    }
    public boolean validate() {
        if (et_nume.getText().toString().isEmpty()) {
            //trimite mesaje catre utilizator
            Toast.makeText(this, "Introduceti numele", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_parola.getText().toString().isEmpty()) {
            //trimite mesaje catre utilizator
            Toast.makeText(this, "Introduceti parola", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void handleButon(){
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (validate()){
                    //preluam ce a scris utilizatorul
                    String nume= et_nume.getText().toString();
                    String parola= et_parola.getText().toString();

                    //cautam utilizatorul in baza de date, daca se afla trecem mai departe, daca nu trebuie sa isi faca cont
                    Utilizator utilizator;
                    utilizator=  Singletone.getInstance(LogInActivity.this).UtilizatorDAO().getByDenumire(nume);
                    if(utilizator != null){
                        if(utilizator.getParola().equals(parola)){
                            //salvam preferinte
                            //setam denumirea fisierului de preferinte
                            SharedPreferences sharedPreferences=getSharedPreferences("preferinte", Context.MODE_PRIVATE);
                            //editorul cu care scriem in fisier
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //data cand s-a logat
                            editor.putString("utilizator_logare", LocalDateTime.now().toString());
                            //tipul de utilizator
                            String tip;
                            Long varsta = Math.abs(ChronoUnit.YEARS.between(LocalDate.now(),utilizator.getDataNastere()));
                            if(varsta >=18){
                                tip= "major";
                            }
                            else{
                                tip="minor";

                            }
                            editor.putString("tip_utilizator",tip);
                            //
                            editor.putString("versiune_so",Build.VERSION.CODENAME);
                            editor.commit();
                            //se logheaza utilizatorul si il trimit in activitatea principala
                            Intent intent = new Intent(LogInActivity.this,ActivitatePrincipala.class);
                            intent.putExtra("utilizator",nume);
                            startActivity(intent);
                            et_nume.getText().clear();
                            et_parola.getText().clear();
                        }
                        else{
                            Toast.makeText(LogInActivity.this,"Parola este gresita",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(LogInActivity.this,"Utilizatorul nu exista",Toast.LENGTH_SHORT).show();
                        finish();
                    }



                }

            }
        });
    }

}