package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class CalificarActivity extends AppCompatActivity {

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
    int estado;

    private EditText comentario;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar);

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
            //imagenTrabajador = extras.getInt("imgProfile");
        }

        TextView calificar = findViewById(R.id.tvActividadaCalificar);
        calificar.setText(trabajo);
        comentario = findViewById(R.id.edtComentario);
        ratingBar = findViewById(R.id.ratingBar);

        String miComent = comentario.getText().toString();
        enviarComentario(miComent);

    }

    private void enviarComentario(String comentario) {

    }

    public void reportar(View view) {
        Intent intent = new Intent(this, ReportarActivity.class);
        startActivity(intent);
    }

    public void enviar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}