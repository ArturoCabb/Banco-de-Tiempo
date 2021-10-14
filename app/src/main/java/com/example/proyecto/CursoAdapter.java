package com.example.proyecto;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.ViewHolderMostrarCurso> implements View.OnClickListener{

    ArrayList<TrabajosModel> listCurso;
    private View.OnClickListener listener;

    public CursoAdapter(ArrayList<TrabajosModel> listCurso) {
        this.listCurso = listCurso;
    }

    @Override
    public ViewHolderMostrarCurso onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curso,null,false);

        view.setOnClickListener(this);
        return new ViewHolderMostrarCurso(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMostrarCurso holder, int position) {
        holder.etiCurso.setText(listCurso.get(position).getNombre());
        holder.etiEstado.setText(listCurso.get(position).getEstado());
        //holder.etiTiempo.setText(listCurso.get(position).getTiempo());
        holder.fotoTrabajador.setImageResource(listCurso.get(position).getImagen());

    }

    @Override
    public int getItemCount() {
        return listCurso.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolderMostrarCurso extends RecyclerView.ViewHolder {

        TextView etiCurso, etiEstado, etiTiempo;
        ImageView fotoTrabajador;

        public ViewHolderMostrarCurso(@NonNull View itemView) {
            super(itemView);
            etiCurso = (TextView) itemView.findViewById(R.id.tvNombreCurso);
            etiEstado = (TextView) itemView.findViewById(R.id.tvEsatdoCurso);
            etiTiempo = (TextView) itemView.findViewById(R.id.tvTiempoCurso);
            fotoTrabajador = (ImageView) itemView.findViewById(R.id.imActividad);
        }
    }
}
