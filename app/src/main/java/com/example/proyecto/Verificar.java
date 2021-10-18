package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicMarkableReference;


public class Verificar extends AppCompatActivity {

    Button buttonine;
    Button buttoncurp;
    Button buttonpena;
    Button buttonGuardar;
    ImageView imageviewIne;
    ImageView imageviewCurp;
    ImageView imageviewpena;

    static final int CAM_REQUEST = 100;
    static final int CAM_REQUEST2 = 200;
    static final int CAM_REQUEST3 = 300;

    Bitmap bitmap1 = null;
    Bitmap bitmap2 = null;
    Bitmap bitmap3 = null;

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        buttonine = findViewById(R.id.buttonIne);
        buttoncurp = findViewById(R.id.buttonCurp);
        buttonpena = findViewById(R.id.buttonPena);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        imageviewIne = findViewById(R.id.imageViewIne);
        imageviewCurp = findViewById(R.id.imageViewCurp);
        imageviewpena = findViewById(R.id.imageViewPena);

        if (ContextCompat.checkSelfPermission(Verificar.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Verificar.this, new String[]{
                    Manifest.permission.CAMERA
            }, CAM_REQUEST);

        }


        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getUid();

        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();

        buttonine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (camera_intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(camera_intent, CAM_REQUEST);
                }
            }
        });

        buttoncurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAM_REQUEST2);

            }
        });

        buttonpena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAM_REQUEST3);

            }
        });


        buttonGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                StorageReference miRef = reference.child("files/comprobante/" + userID + "/" + "INE.jpg");
                imageviewIne.setDrawingCacheEnabled(true);
                imageviewIne.buildDrawingCache();
                bitmap1 = imageviewIne.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = miRef.putBytes(data);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Verificar.this, "Ine Cargado Correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Verificar.this, "Error al cargar la Ine",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                StorageReference miRef1 = reference.child("files/comprobante/" + userID + "/" + "CURP.jpg");
                imageviewCurp.setDrawingCacheEnabled(true);
                imageviewCurp.buildDrawingCache();
                bitmap2 = imageviewCurp.getDrawingCache();
                ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
                byte[] data1 = baos1.toByteArray();
                UploadTask uploadTask1 = miRef1.putBytes(data1);
                uploadTask1.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Verificar.this, "Curp Cargado Correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Verificar.this, "Error al cargar la Curp",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                StorageReference miRef2 = reference.child("files/comprobante/" + userID + "/" + "Penales.jpg");
                imageviewpena.setDrawingCacheEnabled(true);
                imageviewpena.buildDrawingCache();
                bitmap3 = imageviewpena.getDrawingCache();
                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
                byte[] data2 = baos2.toByteArray();
                UploadTask uploadTask2 = miRef2.putBytes(data2);
                uploadTask2.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Verificar.this, "Antecedentes Penales Cargado Correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Verificar.this, "Error al cargar los antecedentes Penales",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAM_REQUEST && resultCode == RESULT_OK){
            bitmap1 = (Bitmap) data.getExtras().get("data");
            imageviewIne.setImageBitmap(bitmap1);
        }
        else if(requestCode == CAM_REQUEST2 && resultCode == RESULT_OK){
            bitmap2 = (Bitmap) data.getExtras().get("data");
            imageviewCurp.setImageBitmap(bitmap2);
        }
        else if(requestCode == CAM_REQUEST3 && resultCode == RESULT_OK){
            bitmap3 = (Bitmap) data.getExtras().get("data");
            imageviewpena.setImageBitmap(bitmap3);
        }
    }
}