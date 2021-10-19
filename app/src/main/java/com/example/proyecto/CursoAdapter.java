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
        String key = listCurso.get(pos).getKey();
        String estado = Integer.toString(listCurso.get(pos).getEstado());
        String yo = listCurso.get(pos).getYo();
        boolean estadoSecundario = listCurso.get(pos).isEstadoSecundario();
        int totalHrs = listCurso.get(pos).getTotalhrs();
        String quienContrata = listCurso.get(pos).getQuienContrata();
        String trabajo = listCurso.get(pos).getTrabajo();

        holder.etiCurso.setText(trabajo);
        holder.etiEstado.setText(estado);
        holder.etiTiempo.setText(quienContrata);

        dbReference = FirebaseDatabase.getInstance().getReference();
        dbReference.child("Users").child(quienContrata).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String url = snapshot.child("urlImageProfile").getValue().toString();
                Glide.with(holder.fotoTrabajador.getContext())
                        .load(url)
                        .placeholder(R.drawable.constructor)
                        .fitCenter()
                        .circleCrop()
                        .into(holder.fotoTrabajador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String user = currentUser.getUid();
                dbReference =  FirebaseDatabase.getInstance().getReference();
                dbReference.child("Users").child(user)
                        .child("trabajos").child(trabajo)
                        .child("estado").setValue(3);
                int currenttotalhrs = totalHrs;
                int totalhrssumadas = 1 + currenttotalhrs;
                dbReference.child("Users").child(user).child("totalhrs").setValue(totalhrssumadas);
                Intent intent = new Intent(view.getContext(), EjecucionTrabajoActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("trabajo", trabajo);
                intent.putExtra("muestroBoton", true);
                view.getContext().startActivity(intent);

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
