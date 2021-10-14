package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MostrarTrabajosFragment extends Fragment{
    public DatabaseReference databaseReference;

    TrabajosModel vo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerTrabajos;
    ArrayList<TrabajosModel> listaTrabajos;
    TrabajosAdapter adapter;

    public MostrarTrabajosFragment() {
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
    public static MostrarTrabajosFragment newInstance(String param1, String param2) {
        MostrarTrabajosFragment fragment = new MostrarTrabajosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mostrar_trabajos, container, false);
        recyclerTrabajos = view.findViewById(R.id.rvMostrarTrabajo);

        listaTrabajos = new ArrayList<>();
        recyclerTrabajos.setLayoutManager(new LinearLayoutManager(getContext()));


        llenarLista();

        adapter = new TrabajosAdapter(listaTrabajos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContratarActivity.class);
                intent.putExtra("nombreTrabajo", listaTrabajos.get(recyclerTrabajos
                        .getChildAdapterPosition(view)).getTrabajo());
                intent.putExtra("nombreTrabajador", listaTrabajos.get(recyclerTrabajos
                        .getChildAdapterPosition(view)).getNombre());
                intent.putExtra("descripcionTrabajo", listaTrabajos.get(recyclerTrabajos
                        .getChildAdapterPosition(view)).getDescripcion());
                intent.putExtra("imgProfile", listaTrabajos.get(recyclerTrabajos
                        .getChildAdapterPosition(view)).getImagen());
                getActivity().startActivity(intent);
            }
        });

        recyclerTrabajos.setAdapter(adapter);
        return view;
    }


    public void llenarLista() {

        databaseReference =  FirebaseDatabase.getInstance().getReference();

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
                                model.getUrlImageProfile(), trabajos.getKey() ,des.getDescripcion(),
                                des.getEstado()));
                            Log.println(Log.ASSERT, "Datos: ", trabajos.toString());
                            Log.println(Log.ASSERT, "Dentro de datosTrabajos", des.toString());
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

    @Override
    public void onStop() {
        super.onStop();
    }
}
