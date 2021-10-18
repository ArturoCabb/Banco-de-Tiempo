package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ContratacionActivity extends AppCompatActivity {

    private String key;
    private String correo;
    private String edad;
    private String hrfin;
    private String hrinicio;
    private String localidad;
    private String nombre;
    private String telefono;
    private String ubicacion;
    private String urlImageProfile;
    private String trabajo;
    private String descripcion;
    private int estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratacion);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key = extras.getString("key");
            correo = extras.getString("correo");
            edad = extras.getString("edad");
            hrfin = extras.getString("hrfin");
            hrinicio = extras.getString("hrinicio");
            localidad = extras.getString("localidad");
            nombre = extras.getString("nombre");
            telefono = extras.getString("telefono");
            ubicacion = extras.getString("ubicacion");
            urlImageProfile = extras.getString("urlImageProfile");
            trabajo = extras.getString("trabajo");
            descripcion = extras.getString("descripcion");
            estado = extras.getInt("estado");
            //imagenTrabajador = extras.getInt("imgProfile");
        }

    }

    public void continuar(View view) {
        Intent intent = new Intent(this, EjecucionTrabajoActivity.class);
        startActivity(intent);
        finish();
    }
}