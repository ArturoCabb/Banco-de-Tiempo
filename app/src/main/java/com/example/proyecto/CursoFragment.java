package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CursoFragment extends Fragment {

    private FirebaseAuth mAuth;
    public DatabaseReference databaseReference;

    FirebaseUser currentUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerCurso;
    ArrayList<TrabajosModel> listaTrabajos;
    CursoAdapter adapter;

    public CursoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CursoFragment newInstance(String param1, String param2) {
        CursoFragment fragment = new CursoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curso, container, false);
        recyclerCurso = view.findViewById(R.id.rvCurso);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        listaTrabajos = new ArrayList<>();
        recyclerCurso.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        adapter = new CursoAdapter(listaTrabajos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContratarActivity.class);
                intent.putExtra("correo", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getCorreo());
                intent.putExtra("edad", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getEdad());
                intent.putExtra("hrfin", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getHrfin());
                intent.putExtra("hrinicio", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getHrinicio());
                intent.putExtra("localidad", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getLocalidad());
                intent.putExtra("nombre", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getNombre());
                intent.putExtra("telefono", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getTelefono());
                intent.putExtra("ubicacion", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getUbicacion());
                intent.putExtra("urlImageProfile", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getUrlImageProfile());
                intent.putExtra("trabajo", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getTrabajo());
                intent.putExtra("descripcion", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getDescripcion());
                intent.putExtra("estado", listaTrabajos.get(recyclerCurso
                        .getChildAdapterPosition(view)).getEstado());
                getActivity().startActivity(intent);
            }
        });

        recyclerCurso.setAdapter(adapter);
        return view;
    }

    private void llenarLista() {
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        String usuario = currentUser.getUid();

        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()) {
                    TrabajosModel model = data.getValue(TrabajosModel.class);
                    for (DataSnapshot trabajos : data.child("trabajos").getChildren()) {
                        TrabajosModel des = trabajos.getValue(TrabajosModel.class);
                        listaTrabajos.add(new TrabajosModel(model.getCorreo(), model.getEdad(),
                                model.getHrfin(), model.getHrinicio(), model.getLocalidad(),
                                model.getNombre(), model.getTelefono(), model.getUbicacion(),
                                model.getUrlImageProfile(), trabajos.getKey(), des.getDescripcion(),
                                des.getEstado()));
                        }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}