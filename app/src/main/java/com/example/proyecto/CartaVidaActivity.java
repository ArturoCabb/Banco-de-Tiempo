package com.example.proyecto;

import static android.content.Intent.ACTION_OPEN_DOCUMENT;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.proyecto.databinding.ActivityCartaVidaBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CartaVidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_vida);
    }

    public void agregar(View view) {
        Intent intent = new Intent(this, AgregarTrabajoActivity.class);
        startActivity(intent);
    }

    public void modificar(View view) {
        Intent intent = new Intent(this, ModificarTrabajoActivity.class);
        startActivity(intent);
    }
    public void eliminar(View view) {
        Intent intent = new Intent(this, EliminarTrabajoActivity.class);
        startActivity(intent);
    }

    public void cerrar(View view) {
        finish();
    }
}