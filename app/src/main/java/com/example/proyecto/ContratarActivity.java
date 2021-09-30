package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContratarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar);

        String nombreTrabajo = "";
        String nombreTrabajador = "";
        String descripcionTrabajo = "";
        int imagenTrabajador = 0;

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            nombreTrabajo = extras.getString("nombreTrabajo");
            nombreTrabajador = extras.getString("nombreTrabajador");
            descripcionTrabajo = extras.getString("descripcionTrabajo");
            imagenTrabajador = extras.getInt("imgProfile");
        }

        TextView trabajo = (TextView) findViewById(R.id.tvNombreTrabajo);
        TextView trabajador = (TextView) findViewById(R.id.tvNombreTrabajador);
        TextView descripcion = (TextView) findViewById(R.id.tvDescripcionTrabajo);
        ImageView foto = (ImageView) findViewById(R.id.imgTrabajador);

        trabajo.setText(nombreTrabajo);
        trabajador.setText(nombreTrabajador);
        descripcion.setText(descripcionTrabajo);
        foto.setImageResource(imagenTrabajador);
    }

    public void cerrar(View view) {
        finish();
    }

    public void contratar(View view) {
        Intent intent = new Intent(this, EjecucionTrabajoActivity.class);
        startActivity(intent);
        finish();
    }
}