package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ContratarActivity extends AppCompatActivity {

    String correo;
    String edad;
    String hrfin;
    String hrinicio;
    String localidad;
    String nombre;
    String telefono;
    String ubicacion;
    String urlImageProfile;
    String trabajo;
    String descripcion;
    int estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar);


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
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

        TextView tvTrabajo = (TextView) findViewById(R.id.tvNombreTrabajo);
        TextView tvTrabajador = (TextView) findViewById(R.id.tvNombreTrabajador);
        TextView tvLugar = (TextView) findViewById(R.id.tvLugarDeTrabajo);
        TextView tvDescripcion = (TextView) findViewById(R.id.tvDescripcionTrabajo);
        ImageView foto = (ImageView) findViewById(R.id.imgTrabajador);

        tvTrabajo.setText(trabajo);
        tvTrabajador.setText(nombre);
        tvLugar.setText(localidad);
        tvDescripcion.setText(descripcion);
        Glide.with(this)
                .load(urlImageProfile)
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .fitCenter()
                .circleCrop()
                .into(foto);
    }

    public void cerrar(View view) {
        finish();
    }

    public void contratar(View view) {
        Intent intent = new Intent(this, EjecucionTrabajoActivity.class);
        intent.putExtra("correo", correo);
        intent.putExtra("edad", edad);
        intent.putExtra("hrfin", hrfin);
        intent.putExtra("hrinicio", hrinicio);
        intent.putExtra("localidad", localidad);
        intent.putExtra("nombre", nombre);
        intent.putExtra("telefono", telefono);
        intent.putExtra("ubicacion", ubicacion);
        intent.putExtra("urlImageProfile", urlImageProfile);
        intent.putExtra("trabajo", trabajo);
        intent.putExtra("descripcion", descripcion);
        intent.putExtra("estado", estado);
        startActivity(intent);
        finish();
    }
}