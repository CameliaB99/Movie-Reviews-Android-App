package com.example.proiectgestiunefilme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;

public class SignInActivity extends AppCompatActivity {
    private EditText et_denumire;
    private EditText et_parola;
    private DatePicker date_data_nastere;
    private Button btn_creaza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();
        handleButon();
    }
    private void initViews(){
        et_denumire=findViewById(R.id.et_nume);
        et_parola = findViewById(R.id.et_parola);
        date_data_nastere=findViewById(R.id.et_data_nastere);
        btn_creaza = findViewById(R.id.btn_creaza_utilizator);
    }
    public boolean validate() {
        if (et_denumire.getText().toString().isEmpty()) {
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
        btn_creaza.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (validate()){
                    //preluam ce a scris utilizatorul
                    String nume= et_denumire.getText().toString();
                    String parola= et_parola.getText().toString();

                    LocalDate data = LocalDate.of(date_data_nastere.getYear(),date_data_nastere.getMonth()+1,date_data_nastere.getDayOfMonth());
                    //creare utilizator
                    Utilizator utilizator = new Utilizator(nume, parola,data);
                    //punem in baza de date utilizatorul
                   /* Singletone.getInstance(SignInActivity.this).UtilizatorDAO().insertUtilizator(utilizator);
                    Log.v("Utilizator", utilizator.toString());
                    finish();*/

                    //varianta asincron
                    Singletone.getInstance(SignInActivity.this).insertUtilizator(utilizator, new ICallbackDB() {
                        @Override
                        public void onSuccess() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v("utilizator","Utilizatorul a fost adaugat");
                                }
                            });

                        }

                        @Override
                        public void onFailure(Throwable error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v("utilizator",error.getLocalizedMessage());
                                }
                            });
                        }


                    });
                    finish(); //ca sa se inchida activitatea
                }

            }
        });
    }


}