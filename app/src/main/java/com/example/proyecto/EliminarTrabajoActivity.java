package com.example.proyecto;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.databinding.ActivityCartaVidaBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EliminarTrabajoActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCartaVidaBinding binding;


    Uri FILEBMP =null;
    String userID;
    EditText nombre;
    Button eliminar;

    static final int REQUEST_FILE = 1;

    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private FirebaseStorage storage;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_trabajo);
        nombre = (EditText) findViewById(R.id.editTextNombre);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, REQUEST_FILE);

        }

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getUid();

        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();

        database = FirebaseDatabase.getInstance().getReference();

        eliminar = (Button) findViewById(R.id.button);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nombre.getText().toString();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("trabajos").child(nom);
                ref.removeValue();
                //ref.child("Users").child(userID).child("trabajos").equalTo(nom);

                Toast.makeText(EliminarTrabajoActivity.this, "Trabajo Eliminado",
                        Toast.LENGTH_SHORT).show();


            }
        });



    }

    public void archivo(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_FILE);
    }


    public void eliminar(View view) {

    }

    public void cerrar(View view) {
        finish();
    }

    public void openDirectory(Uri uriToLoad) {
        // Choose a directory using the system's file picker.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

        // Provide read access to files and sub-directories in the user-selected
        // directory.
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivityForResult(intent, REQUEST_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == REQUEST_FILE
                && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            if (resultData != null) {
                FILEBMP = resultData.getData();
                // Perform operations on the document using its URI.
            }
        }
    }
}