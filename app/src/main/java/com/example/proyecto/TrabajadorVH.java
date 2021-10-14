package com.example.proyecto;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrabajadorVH  extends RecyclerView.ViewHolder {
    public TextView txt_trabajo, txt_trabajador;
    public TrabajadorVH(@NonNull View itemView) {
        super(itemView);
        txt_trabajo = itemView.findViewById(R.id.tvNombreTrabajo);
        txt_trabajador = itemView.findViewById(R.id.tvNombreTrabajador);

    }
}
