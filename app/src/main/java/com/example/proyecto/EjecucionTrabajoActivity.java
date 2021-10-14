package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class EjecucionTrabajoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_ejecucion_trabajo);

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


        ImageView img = findViewById(R.id.imgTrabajoEjecucion);
        TextView nombre1 = findViewById(R.id.tvNombrePersonaEjecucion);
        TextView horaI = findViewById(R.id.tvHoraInicioEjecucion);
        TextView horaF = findViewById(R.id.tvHoraFinEjecucion);

        nombre1.setText(nombre);
        horaI.setText(hrinicio);
        horaF.setText(hrfin);
        Glide.with(this)
                .load(urlImageProfile)
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .fitCenter()
                .circleCrop()
                .into(img);
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

    public void terminar(View view) {
        Intent intent = new Intent(this, CalificarActivity.class);
        startActivity(intent);
        finish();
    }
}