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

public class SignUpActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";
    private static final String USER = "user";
    private Usuario usuario;

    private ImageView imagenPerfil;
    private EditText x_usuario, x_email, x_pass, x_pass2;
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
        btnRegistro = findViewById(R.id.btnSignUp);

        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        mAuth = FirebaseAuth.getInstance();



        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = x_email.getText().toString().trim();
                String pass = x_pass.getText().toString();
                String pass2 = x_pass2.getText().toString();
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


                registerUser(email, pass);


            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void registerUser(String email, String pass){
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public void updateUI(FirebaseUser currentuser){
        String keyId = mDatabase.push().getKey();
        mDatabase.child(keyId).setValue(usuario);
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);

    }

}