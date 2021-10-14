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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TrabajosAdapter extends RecyclerView.Adapter<TrabajosAdapter.ViewHolderMostrarTrabajos> implements View.OnClickListener{

    ArrayList<TrabajosModel> listTrabajos;
    private View.OnClickListener listener;

    public TrabajosAdapter(ArrayList<TrabajosModel> listTrabajos) {
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
        holder.etiTrabajo.setText(listTrabajos.get(position).getTrabajo());
        holder.etiTrabajador.setText(listTrabajos.get(position).getNombre());
        holder.etiDescripcionTrabajo.setText(listTrabajos.get(position).getDescripcion());
        Glide.with(holder.fotoTrabajador.getContext())
                .load(listTrabajos.get(position).getUrlImagen())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .fitCenter()
                .circleCrop()
                .into(holder.fotoTrabajador);
        //holder.fotoTrabajador.setImageResource(listTrabajos.get(position).getImagen());

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

        String urlImage;
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
