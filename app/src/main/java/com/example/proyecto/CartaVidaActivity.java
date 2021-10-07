package com.example.proyecto;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.proyecto.databinding.ActivityCartaVidaBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CartaVidaActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCartaVidaBinding binding;

    String userID;
    EditText actividad;
    EditText descripcion;

    private FirebaseAuth mAuth;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_vida);
        actividad = findViewById(R.id.edtActividadDesempenada);
        descripcion = findViewById(R.id.edtDescripcionCartaVida);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getUid();

        database = FirebaseDatabase.getInstance().getReference();

    }

    public void archivo(View view) {

    }

    public void guardar(View view) {
        String act = actividad.getText().toString();
        String des = descripcion.getText().toString();

        if(TextUtils.isEmpty(act) || TextUtils.isEmpty(des)) {
            CartaVida datos = new CartaVida(des, 0);
            database.child("trabajos").child(userID).child(act).push().setValue(datos);
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();   
        }
        else {
            Toast.makeText(this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(View view) {

    }
}