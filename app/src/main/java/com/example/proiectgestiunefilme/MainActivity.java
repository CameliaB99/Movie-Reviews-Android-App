package com.example.proiectgestiunefilme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button login;
private Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.buton_login);
        signin=findViewById(R.id.button_signin);
        evenimentPeButon();
    }
    public void evenimentPeButon(){
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cls Intent trimite mesaje si leaga activitatile intre ele prin butoane
                //legatura dintre clase
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cls Intent trimite mesaje si leaga activitatile intre ele prin butoane
                //legatura dintre clase
                Intent intent = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}