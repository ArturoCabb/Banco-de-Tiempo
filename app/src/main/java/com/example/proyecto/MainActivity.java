package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navegacion_inicio:
                    getSupportFragmentManager().beginTransaction().add(R.id.view_principal, new MostrarTrabajosFragment()).commit();
                    return true;
                case R.id.navegacion_actividades:
                    getSupportFragmentManager().beginTransaction().replace(R.id.view_principal, new CursoFragment()).commit();
                    return true;
                case R.id.navegacion_chat:
                    getSupportFragmentManager().beginTransaction().replace(R.id.view_principal, new MessageFragment()).commit();
                    return true;
                case R.id.navegacion_perfil:
                    getSupportFragmentManager().beginTransaction().replace(R.id.view_principal, new ProfileFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}