package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    MostrarTrabajosFragment trabajosFragment = new MostrarTrabajosFragment();
    CursoFragment cursoFragment = new CursoFragment();
    MessageFragment messageFragment = new MessageFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(trabajosFragment);
    }

    private final BottomNavigationView.OnItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navegacion_inicio:
                    loadFragment(trabajosFragment);
                    return true;
                case R.id.navegacion_actividades:
                    loadFragment(cursoFragment);
                    return true;
                case R.id.navegacion_chat:
                    loadFragment(messageFragment);
                    return true;
                case R.id.navegacion_perfil:
                    loadFragment(profileFragment);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.view_principal, fragment);
        transaction.commit();
    }
}