package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navegacion_inicio:
                    //getSupportFragmentManager().beginTransaction().add(R.id., new InicioFragment()).commit();
                    return true;
                case R.id.navegacion_chat:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.navegacion_chat, new ).commit();
                    return true;
                case R.id.navegacion_perfil:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.navegacion_perfil, new ).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}