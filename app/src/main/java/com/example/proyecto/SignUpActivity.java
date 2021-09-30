package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase Database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private final String TAG = "MainActivity";

    public void aute(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        View usernmView = findViewById(R.id.etCreaUsuario);
        String usernm = usernmView.toString();
        View emailView = findViewById(R.id.editTextTextEmailAddress);
        String email = emailView.toString();
        View passwordView = findViewById(R.id.etCreaContrasena);
        String password = passwordView.toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            mAuth = FirebaseAuth.getInstance();
                            mDatabase.child("users").child("test").child("username").setValue(email);
                            User user1 = new User(usernm, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "registrado", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(SignUpActivity.this, "Fail", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            //If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Fail", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                    private void updateUI(FirebaseUser user) {
                    }
                });
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
    }

    @Override
    public void onClick(View view) {

    }
}