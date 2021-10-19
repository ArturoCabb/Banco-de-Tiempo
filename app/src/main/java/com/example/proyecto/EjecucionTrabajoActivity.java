package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.OnReceiveContentListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class EjecucionTrabajoActivity extends AppCompatActivity {

    String key;
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
    boolean muestroBoton;
    int estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejecucion_trabajo);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
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
            muestroBoton = extras.getBoolean("muestroBoton");
            //imagenTrabajador = extras.getInt("imgProfile");
        }


        ImageView img = findViewById(R.id.imgTrabajoEjecucion);
        TextView nombre1 = findViewById(R.id.tvNombrePersonaEjecucion);
        TextView horaI = findViewById(R.id.tvHoraInicioEjecucion);
        TextView horaF = findViewById(R.id.tvHoraFinEjecucion);
        TextView mail = findViewById(R.id.TEcorreo);
        TextView phone = findViewById(R.id.TEtelefono);
        Button miboton = findViewById(R.id.btTerminarEjecucion);
        if (muestroBoton)
            miboton.setVisibility(View.INVISIBLE);

        nombre1.setText(nombre);
        horaI.setText("Hora inicio: " + hrinicio);
        horaF.setText("Hora fin: " + hrfin);
        mail.setText(correo);
        phone.setText(telefono);
        Glide.with(this)
                .load(urlImageProfile)
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .fitCenter()
                .into(img);
    }

    public void cerrar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void reportar(View view) {
        Intent intent = new Intent(this, ReportarActivity.class);
        intent.putExtra("key", key);
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
    }

    public void terminar(View view) {
        Intent intent = new Intent(this, CalificarActivity.class);
        intent.putExtra("key", key);
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