package com.example.proyecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.concurrent.atomic.AtomicMarkableReference;


public class Verificar extends AppCompatActivity {

    Button buttonine;
    Button buttoncurp;
    Button buttonGuardar;
    ImageView imageviewIne;
    ImageView imageviewCurp;
    static final int CAM_REQUEST = 1;

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
            }, 100);

        }

        buttoncurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });

        buttonine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);


            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


            }

        });


    }
    private File getFile()
    {
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
        if(requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageviewCurp.setImageBitmap(bitmap);
        }
    }
}