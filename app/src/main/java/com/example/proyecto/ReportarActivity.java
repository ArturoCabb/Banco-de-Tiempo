package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.Key;

public class ReportarActivity extends AppCompatActivity {

    String key;
    String quienContrata;
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
    EditText edtReporte;
    Button Enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);
        edtReporte = findViewById(R.id.edtReporte);
        Enviar = findViewById(R.id.btEnviar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key = extras.getString("key");
            quienContrata = extras.getString("quienContrata");
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
        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:bancodetiempoedomex@gmail.com"));
                emailIntent.putExtra(Intent.EXTRA_TEXT,edtReporte.getText().toString());
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                startActivity(emailIntent);
            }
        });
    }

    public void cerrar(View view) {
        finish();
    }

    public void enviar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}