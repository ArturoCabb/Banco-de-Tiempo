package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.OnReceiveContentListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EjecucionTrabajoActivity extends AppCompatActivity {

    public DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();

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
    boolean muestroBoton;
    int estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejecucion_trabajo);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
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

        horaI.setText("Hora inicio: " + hrinicio);
        horaF.setText("Hora fin: " + hrfin);
        mail.setText(correo);
        phone.setText(telefono);

        if (muestroBoton){
        dbReference.child("Users").child(quienContrata).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = snapshot.child("nombre").getValue().toString();
                EjecucionTrabajoActivity.this.nombre = nombre;
                nombre1.setText(nombre);
                Glide.with(EjecucionTrabajoActivity.this)
                        .load(snapshot.child("urlImageProfile").getValue().toString())
                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .fitCenter()
                        .into(img);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        } else {
            nombre1.setText(nombre);
            Glide.with(this)
                    .load(urlImageProfile)
                    .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                    .fitCenter()
                    .into(img);
        }
    }

    public void cerrar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void reportar(View view) {
        Intent intent = new Intent(this, ReportarActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("quienContrata", quienContrata);
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
        dbReference.child("Users").child(key).child("trabajos").child(trabajo).child("estado").setValue(0);
        dbReference.child("Users").child(key).child("trabajos").child(trabajo).child("quienContrata").setValue("");
        Intent intent = new Intent(this, CalificarActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("quienContrata", quienContrata);
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