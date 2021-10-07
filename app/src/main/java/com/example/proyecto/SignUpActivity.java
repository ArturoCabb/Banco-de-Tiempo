package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";
    private static final String USER = "user";
    private Usuario usuario;

    private ImageView imagenPerfil;
    private EditText x_usuario, x_email, x_pass, x_pass2, x_edad, x_localidad, x_telefono;
    private Button btnRegistro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        imagenPerfil = findViewById(R.id.imgIcono4);
        x_usuario = findViewById(R.id.etCreaUsuario);
        x_email = findViewById(R.id.editTextTextEmailAddress);
        x_pass = findViewById(R.id.etCreaContrasena);
        x_pass2 = findViewById(R.id.etConfirmaContrasena);
        x_edad = findViewById(R.id.etEdad);
        x_localidad = findViewById(R.id.etLocalidad);
        btnRegistro = findViewById(R.id.btnSignUp);
        x_telefono = findViewById(R.id.ettelefono);


        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        mAuth = FirebaseAuth.getInstance();



        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = x_email.getText().toString().trim();
                String pass = x_pass.getText().toString();
                String pass2 = x_pass2.getText().toString();
                String edad = x_edad.getText().toString();
                String localidad = x_localidad.getText().toString();
                String telefono = x_telefono.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Escriba su Email y Contraseña", Toast.LENGTH_LONG).show();
                    return;
                }
                if (pass.length() < 6){
                    Toast.makeText(getApplicationContext(), "Contraseña muy corta", Toast.LENGTH_LONG).show();
                    return;
                }
                if  (!TextUtils.equals(pass, pass2)){
                    Toast.makeText(getApplicationContext(), "Las Contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }
                String nombre = x_usuario.getText().toString();
                if (TextUtils.isEmpty(nombre)){
                    Toast.makeText(getApplicationContext(), "Escriba su Nombre", Toast.LENGTH_LONG).show();
                    return;
                }
                usuario = new Usuario(email,pass, nombre);


                registerUser(email, pass, nombre, edad, localidad, telefono);


            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void registerUser(String email, String pass, String nombre, String edad, String localidad, String telefono){
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //Log.d(TAG, "createUserWithEmail:failure");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {

                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                            Map newPost = new HashMap();
                            newPost.put("nombre", nombre);
                            newPost.put("edad", edad);
                            newPost.put("localidad", localidad);
                            newPost.put("telefono", telefono);

                            current_user_db.setValue(newPost);

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        }
                    }
                });


    }




    public void updateUI(FirebaseUser currentuser){
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);

    }

}