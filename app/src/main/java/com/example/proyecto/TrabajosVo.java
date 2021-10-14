package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class TrabajosVo extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public Query get() {
        return databaseReference.orderByKey();
    }

    private Context context;
    ArrayList<TrabajosVo> list = new ArrayList<>();
    public TrabajosVo (Context ctx)
    {
        this.context = ctx;
    }

    public void setItems(ArrayList<TrabajosVo> trabajador)
    {
        list.addAll(trabajador);
    }

    private String nombreTrabajo;
    private String nombreTrabajador;
    private String descripcionTrabajo;
    private int fotoTrabajador;

    public TrabajosVo() {


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trabajos,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TrabajadorVH vh = (TrabajadorVH) holder;
        TrabajosVo trabajador = list.get(Integer.parseInt(nombreTrabajo));
        vh.txt_trabajador.setText(trabajador.getNombreTrabajador());
        vh.txt_trabajo.setText(trabajador.getNombreTrabajo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public TrabajosVo(String nombreTrabajo, String nombreTrabajador, String descripcionTrabajo, int fotoTrabajador) {
        this.nombreTrabajo = nombreTrabajo;
        this.nombreTrabajador = nombreTrabajador;
        this.descripcionTrabajo = descripcionTrabajo;
        this.fotoTrabajador = fotoTrabajador;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        nombreTrabajador = nombreTrabajador;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo() {
        descripcionTrabajo = descripcionTrabajo;
    }

    public int getFotoTrabajador() {
        return fotoTrabajador;
    }

    public void setFotoTrabajador(int fotoTrabajador) {
        this.fotoTrabajador = fotoTrabajador;
    }
}
