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

public class TrabajosAdapter extends RecyclerView.Adapter<TrabajosAdapter.ViewHolderMostrarTrabajos> implements View.OnClickListener{

    ArrayList<TrabajosVo> listTrabajos;
    private View.OnClickListener listener;

    public TrabajosAdapter(ArrayList<TrabajosVo> listTrabajos) {
        this.listTrabajos = listTrabajos;
    }

    @Override
    public ViewHolderMostrarTrabajos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trabajos,null,false);

        view.setOnClickListener(this);
        return new ViewHolderMostrarTrabajos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMostrarTrabajos holder, int position) {
        holder.etiTrabajo.setText(listTrabajos.get(position).getNombreTrabajo());
        holder.etiTrabajador.setText(listTrabajos.get(position).getNombreTrabajador());
        holder.etiDescripcionTrabajo.setText(listTrabajos.get(position).getDescripcionTrabajo());
        holder.fotoTrabajador.setImageResource(listTrabajos.get(position).getFotoTrabajador());

    }



    @Override
    public int getItemCount() {
        return listTrabajos.size();
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

    public class ViewHolderMostrarTrabajos extends RecyclerView.ViewHolder {

        TextView etiTrabajo, etiTrabajador, etiDescripcionTrabajo;
        ImageView fotoTrabajador;

        public ViewHolderMostrarTrabajos(@NonNull View itemView) {
            super(itemView);
            etiTrabajo = (TextView) itemView.findViewById(R.id.tvNombreTrabajo);
            etiTrabajador = (TextView) itemView.findViewById(R.id.tvNombreTrabajador);
            etiDescripcionTrabajo = (TextView) itemView.findViewById(R.id.tvDescripcionTrabajo);
            fotoTrabajador = (ImageView) itemView.findViewById(R.id.imgTrabajador);
        }
    }
}
