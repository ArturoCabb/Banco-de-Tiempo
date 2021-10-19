package com.example.proyecto;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.ViewHolderMostrarCurso> implements View.OnClickListener{

    ArrayList<TrabajosModel> listCurso;
    private View.OnClickListener listener;
    public DatabaseReference dbReference;
    private FirebaseAuth mAuth;



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
        int pos = holder.getAdapterPosition();
        String estado = Integer.toString(listCurso.get(position).getEstado());
        String yo = listCurso.get(pos).getYo();
        String trabajo = listCurso.get(pos).getTrabajo();
        String url = listCurso.get(pos).getUrlImageProfile();
        String nombre = listCurso.get(pos).getNombre();
        String correo = listCurso.get(pos).getCorreo();
        String telefono = listCurso.get(pos).getTelefono();

        holder.etiCurso.setText(trabajo);
        holder.etiEstado.setText(estado);
        holder.etiTiempo.setText(listCurso.get(position).getHrinicio());
        Glide.with(holder.fotoTrabajador.getContext())
                .load(url)
                .placeholder(R.drawable.constructor)
                .fitCenter()
                .circleCrop()
                .into(holder.fotoTrabajador);

        holder.btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String user = currentUser.getUid().toString();
                dbReference =  FirebaseDatabase.getInstance().getReference();
                dbReference.child("Users").child(listCurso.get(pos).getRecibe())
                        .child("trabajos").child(listCurso.get(pos).getTrabajo())
                        .child("estado").setValue(3);
                int currenttotalhrs = Integer.parseInt(dbReference.child("Users").child(user).child("totalhrs").toString());

                int totalhrssumadas = 1 + currenttotalhrs;
                Intent intent = new Intent(view.getContext(), EjecucionTrabajoActivity.class);
                intent.putExtra("correo", correo);
                intent.putExtra("telefono", telefono);
                intent.putExtra("nombre", nombre);
                intent.putExtra("urlImageProfiel", url);
                intent.putExtra("trabajo", trabajo);
                intent.putExtra("muestroBoton", yo);
                view.getContext().startActivity(intent);
                dbReference.child("Users").child(user).child("totalhrs").setValue(totalhrssumadas);
            }
        });
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
        Button btnAceptar;

        public ViewHolderMostrarCurso(@NonNull View itemView) {
            super(itemView);
            etiCurso = (TextView) itemView.findViewById(R.id.tvNombreCurso);
            etiEstado = (TextView) itemView.findViewById(R.id.tvEsatdoCurso);
            etiTiempo = (TextView) itemView.findViewById(R.id.tvTiempoCurso);
            fotoTrabajador = (ImageView) itemView.findViewById(R.id.imActividad);
            btnAceptar = (Button) itemView.findViewById(R.id.btAceptarActividad);
        }
    }
}
