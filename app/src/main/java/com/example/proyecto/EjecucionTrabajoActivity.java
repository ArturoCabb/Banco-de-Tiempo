package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EjecucionTrabajoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejecucion_trabajo);
    }

    public void cerrar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void reportar(View view) {
        Intent intent = new Intent(this, ReportarActivity.class);
        startActivity(intent);
    }

    public void mensaje(View view) {
    }

    public void terminar(View view) {
        Intent intent = new Intent(this, CalificarActivity.class);
        startActivity(intent);
        finish();
    }
}