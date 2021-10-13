package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ModificarPerfilActivity extends AppCompatActivity {

    private ImageView profileImage;

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private StorageReference reference;
    static final int CAM_REQUEST = 100;

    Bitmap bitmap = null;
    String urlUri;

    public ModificarPerfilActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_perfil);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        profileImage = findViewById(R.id.imgPerfilModificar);
        final EditText nombre  = findViewById(R.id.edModificarNombre);
        final EditText localidad = findViewById(R.id.edtModificarUbicacion);
        final EditText correo = findViewById(R.id.edtModificarCorreo);
        final EditText telefono = findViewById(R.id.edtModificarTelefono);
        final EditText ubicacion = findViewById(R.id.edtModificarLocalizacion);
        final EditText entrada = findViewById(R.id.edtModificarHoraInicio);
        final EditText salida = findViewById(R.id.edtModificarHoraFin);
        Button btnSaveChanges = findViewById(R.id.btGuardar);
        Button btnEditImage = findViewById(R.id.btModificarImagenPerfil);
        ImageButton btnCerrar = findViewById(R.id.btnCerrarModificarPerfil);

        if (ContextCompat.checkSelfPermission(ModificarPerfilActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(ModificarPerfilActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, CAM_REQUEST);

        }

        String user_id = currentUser.getUid();
        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();

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

                    String url = snapshot.child(user_id).child("urlImageProfile").getValue().toString();
                    if (url != "") {
                        Glide.with(ModificarPerfilActivity.this)
                                .load(url)
                                .fitCenter()
                                .circleCrop()
                                .into(profileImage);
                    }
                }else{

                    nombre.setText("Error snapshot");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                nombre.setText("Error");

            }
        });


        btnEditImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (camera_intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(camera_intent, CAM_REQUEST);
                }
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


                StorageReference miRef = reference.child("Profile_picture/" + user_id + "/" +
                        "profileImage.jpg");
                profileImage.setDrawingCacheEnabled(true);
                profileImage.buildDrawingCache();
                bitmap = profileImage.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = miRef.putBytes(data);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful());
                        Uri downloadUri = uriTask.getResult();
                        urlUri = downloadUri.toString();

                        Toast.makeText(ModificarPerfilActivity.this, "Imagen de " +
                                        "Perfil Cargado Correctamente",
                                Toast.LENGTH_SHORT).show();

                        updateDatabase(perfil,ubi,email,phone,loc,begin,end, urlUri, mdatabase, currentUser);
                    updateUI(currentUser);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ModificarPerfilActivity.this, "Error al cargar" +
                                        " la Imagen de Perfil",
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAM_REQUEST && resultCode == RESULT_OK){
            bitmap = (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(bitmap);
        }
    }



    private void updateUI(FirebaseUser currentUser) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void updateDatabase(String perfil, String ubi, String email, String phone, String loc,
                               String begin, String end, String urlUri, DatabaseReference mdatabase,
                               FirebaseUser currentUser) {
        String user_id = currentUser.getUid();
        mdatabase.child("Users").child(user_id).child("nombre").setValue(perfil);
        mdatabase.child("Users").child(user_id).child("localidad").setValue(ubi);
        mdatabase.child("Users").child(user_id).child("correo").setValue(email);
        mdatabase.child("Users").child(user_id).child("telefono").setValue(phone);
        mdatabase.child("Users").child(user_id).child("ubicacion").setValue(loc);
        mdatabase.child("Users").child(user_id).child("hrinicio").setValue(begin);
        mdatabase.child("Users").child(user_id).child("hrfin").setValue(end);
        mdatabase.child("Users").child(user_id).child("urlImageProfile").setValue(urlUri);
    }
}