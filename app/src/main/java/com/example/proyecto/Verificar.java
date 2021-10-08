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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicMarkableReference;


public class Verificar extends AppCompatActivity {

    Button buttonine;
    Button buttoncurp;
    Button buttonGuardar;
    ImageView imageviewIne;
    ImageView imageviewCurp;

    static final int CAM_REQUEST = 100;
    static final int CAM_REQUEST2 = 200;

    Bitmap bitmap1 = null;
    Bitmap bitmap2 = null;

    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private FirebaseStorage storage;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        buttonine = findViewById(R.id.buttonIne);
        buttoncurp = findViewById(R.id.buttonCurp);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        imageviewIne = findViewById(R.id.imageViewIne);
        imageviewCurp = findViewById(R.id.imageViewCurp);

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

        database = FirebaseDatabase.getInstance().getReference();


        buttoncurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAM_REQUEST2);

            }
        });

        buttonine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (camera_intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(camera_intent, CAM_REQUEST);

                }
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Uri imageUriINE = getUri(bitmap1, userID, "INE");
                StorageReference miRef = reference.child("files/comprobante/" + userID + "/" + "INE.jpg");
                miRef.putFile(imageUriINE).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Verificar.this, "Ine Cargado Correctamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Verificar.this, "Error al cargar la Ine", Toast.LENGTH_SHORT).show();
                    }
                });
                Uri imageUriCURP = getUri(bitmap2, userID, "CURP");
                StorageReference miRef1 = reference.child("files/comprobante/" + userID + "/" + "CURP.jpg");
                miRef1.putFile(imageUriCURP).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Verificar.this, "Curp Cargado Correctamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Verificar.this, "Error al cargar la Curp", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private Uri getUri(Bitmap bitmap,String userID, String tipo) {
        OutputStream fos = null;
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        String fileName = userID + tipo;
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");
        values.put(MediaStore.Images.Media.IS_PENDING, 1);
        Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        Uri imageUri = resolver.insert(collection, values);
        try {
            fos = resolver.openOutputStream(imageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        values.clear();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        return imageUri;
    }

    private File getFile() {
        File folder = new File ("sdcard/TiempoCompartido");

        if (!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = new File (folder,"camimage.jpg");

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAM_REQUEST && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bitmap1 = (Bitmap) extras.get("data");
            imageviewIne.setImageBitmap(bitmap1);
        }
        if(requestCode == CAM_REQUEST2){
            Bundle extras = data.getExtras();
            bitmap2 = (Bitmap) extras.get("data");
            imageviewCurp.setImageBitmap(bitmap2);
        }
    }
}