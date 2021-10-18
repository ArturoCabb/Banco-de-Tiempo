package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ContratarActivity extends AppCompatActivity {

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


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratar);

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

        TextView tvTrabajo = (TextView) findViewById(R.id.tvNombreTrabajo);
        TextView tvTrabajador = (TextView) findViewById(R.id.tvNombreTrabajador);
        TextView tvLugar = (TextView) findViewById(R.id.tvLugarDeTrabajo);
        TextView tvDescripcion = (TextView) findViewById(R.id.tvDescripcionTrabajo);
        ImageView foto = (ImageView) findViewById(R.id.imgTrabajador);
        TextView tvmail = (TextView) findViewById(R.id.contratarmail);
        TextView tvtelefono = (TextView) findViewById(R.id.contratartelefono);

        tvTrabajo.setText(trabajo);
        tvTrabajador.setText(nombre);
        tvLugar.setText(localidad);
        tvDescripcion.setText(descripcion);
        tvmail.setText(correo);
        tvtelefono.setText(telefono);
        Glide.with(this)
                .load(urlImageProfile)
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .fitCenter()
                //.circleCrop()
                .into(foto);
    }

    public void cerrar(View view) {
        finish();
    }

    public void contratar(View view) {
        mAuth = FirebaseAuth.getInstance();
        String currentUser = mAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference()
        .child("Users").child(key).child("trabajos").child(trabajo);
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int es = Integer.parseInt(snapshot.child("estado").getValue().toString());
                estado = es;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (estado == 3) {
            Toast.makeText(this, "La actividad ya está en curso", Toast.LENGTH_SHORT).show();
            irAEjecucion();
        } else if (estado == 0) {
            mdatabase.child("estado").setValue(1);
            mdatabase.child("quienContrata").setValue(currentUser);
            Toast.makeText(this, "Solicitud enviada, espere a que acepte la otra persona", Toast.LENGTH_SHORT).show();
        }
    }

    private void irAEjecucion() {
        Intent intent = new Intent(this, ContratacionActivity.class);
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