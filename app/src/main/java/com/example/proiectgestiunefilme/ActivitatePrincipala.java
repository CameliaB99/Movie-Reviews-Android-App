package com.example.proiectgestiunefilme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.example.proiectgestiunefilme.ui.AdaugaFilm;
import com.example.proiectgestiunefilme.ui.AdaugaRating;
import com.example.proiectgestiunefilme.ui.DetaliiCinematograf;
import com.example.proiectgestiunefilme.ui.ListaFilme;
import com.example.proiectgestiunefilme.ui.ListaSeriale;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivitatePrincipala extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawerLayout;
    private Utilizator utilizator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate_principala); //deseneaza fereastra
        configurareMeniu();
        Intent intent=getIntent();
        if(intent != null ){
            if(intent.hasExtra("utilizator")){
                String denumire = intent.getSerializableExtra("utilizator").toString();
                utilizator=  Singletone.getInstance(this).UtilizatorDAO().getByDenumire(denumire);

            }
        }



    }
    public void configurareMeniu(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //adaug setare de sters cont in activitate principala
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_settings:{
                        Singletone.getInstance(ActivitatePrincipala.this).stergeUtilizator(utilizator, new ICallbackDB() {
                            @Override
                            public void onSuccess() {

                                Log.v("utilizator","Sterge utilizator");
                                finish();
                            }

                            @Override
                            public void onFailure(Throwable error) {
                                Toast.makeText(ActivitatePrincipala.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }


                        });
                        break;
                    }

                }
                return true;
            }
        });
        drawerLayout = findViewById(R.id.drawer_layout);

        //conexiune intre toolbar si drawerlayout
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        //stringuri pentru deschidere si inchidere, nu se vor afisa
        //R. => clasa pentru acces la toate resursele create/aduagate

        drawerLayout.addDrawerListener(drawerToggle);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //metoda de pe actionbar -> metoda care stie cand se inchide/deschide drawer
        drawerToggle.syncState();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activitate_principala, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.lista_filme:
            {
                schimbaFragmente(new ListaFilme(utilizator));
                break;
            }
            case R.id.lista_seriale:
            {
                schimbaFragmente(new ListaSeriale());
                break;
            }
            case R.id.detalii_cinematograf:
            {schimbaFragmente(new DetaliiCinematograf());
                break;
            }
            case R.id.adauga_film:
            {
                schimbaFragmente(new AdaugaFilm(utilizator));
                break;
            }
            case R.id.adauga_rating:
            {   schimbaFragmente(new AdaugaRating(utilizator));
                break;
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void schimbaFragmente(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();

    }
}