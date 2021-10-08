package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModificarPerfilActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    public ModificarPerfilActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_perfil);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        final EditText nombre  = findViewById(R.id.edModificarNombre);
        final EditText localidad = findViewById(R.id.edtModificarUbicacion);
        final EditText correo = findViewById(R.id.edtModificarCorreo);
        final EditText telefono = findViewById(R.id.edtModificarTelefono);
        final EditText ubicacion = findViewById(R.id.edtModificarLocalizacion);
        final EditText entrada = findViewById(R.id.edtModificarHoraInicio);
        final EditText salida = findViewById(R.id.edtModificarHoraFin);
        Button btnSaveChanges = findViewById(R.id.btGuardar);
        Button btnEditImage = findViewById(R.id.btModificarImagenPerfil);

        String user_id = currentUser.getUid();

        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();

        mdatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String perfil = snapshot.child(user_id).child("nombre").getValue().toString();
                    nombre.setText(perfil);

                    String ubi = snapshot.child(user_id).child("localidad").getValue().toString();
                    localidad.setText(ubi);

                    String email = snapshot.child(user_id).child("correo").getValue().toString();
                    correo.setText(email);

                    String phone = snapshot.child(user_id).child("telefono").getValue().toString();
                    telefono.setText(phone);

                    String loc = snapshot.child(user_id).child("ubicacion").getValue().toString();
                    if(loc != "-") {   ubicacion.setText(loc);   }

                    String begin = snapshot.child(user_id).child("hrinicio").getValue().toString();
                    if(begin != "-") {   entrada.setText(begin);   }

                    String end = snapshot.child(user_id).child("hrfin").getValue().toString();
                    if(end != "-") {   salida.setText(end);   }

                }else{

                    nombre.setText("Error snapshot");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                nombre.setText("Error");

            }
        });
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String perfil = nombre.getText().toString();
                final String ubi = localidad.getText().toString();
                final String email = correo.getText().toString();
                final String phone = telefono.getText().toString();
                final String loc = ubicacion.getText().toString();
                final String begin = entrada.getText().toString();
                final String end = salida.getText().toString();
                updateDatabase(perfil,ubi,email,phone,loc,begin,end,mdatabase, currentUser);
                updateUI(currentUser);
            }
        });

    }

    private void updateUI(FirebaseUser currentUser) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void updateDatabase(String perfil, String ubi, String email, String phone, String loc, String begin, String end, DatabaseReference mdatabase, FirebaseUser currentUser) {
        String user_id = currentUser.getUid();
        mdatabase.child("Users").child(user_id).child("nombre").setValue(perfil);
        mdatabase.child("Users").child(user_id).child("localidad").setValue(ubi);
        mdatabase.child("Users").child(user_id).child("correo").setValue(email);
        mdatabase.child("Users").child(user_id).child("telefono").setValue(phone);
        mdatabase.child("Users").child(user_id).child("ubicacion").setValue(loc);
        mdatabase.child("Users").child(user_id).child("hrinicio").setValue(begin);
        mdatabase.child("Users").child(user_id).child("hrfin").setValue(end);
    }
}