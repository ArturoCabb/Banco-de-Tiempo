package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MostrarTrabajosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerTrabajos;
    ArrayList<TrabajosVo> listaTrabajos;

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

        TrabajosAdapter adapter = new TrabajosAdapter(listaTrabajos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContratarActivity.class);
                intent.putExtra("nombreTrabajo", listaTrabajos.get(recyclerTrabajos.getChildAdapterPosition(view)).getNombreTrabajo());
                intent.putExtra("nombreTrabajador", listaTrabajos.get(recyclerTrabajos.getChildAdapterPosition(view)).getNombreTrabajador());
                intent.putExtra("descripcionTrabajo", listaTrabajos.get(recyclerTrabajos.getChildAdapterPosition(view)).getDescripcionTrabajo());
                intent.putExtra("imgProfile", listaTrabajos.get(recyclerTrabajos.getChildAdapterPosition(view)).getFotoTrabajador());
                getActivity().startActivity(intent);
            }
        });

        recyclerTrabajos.setAdapter(adapter);
        return view;
    }

    private void llenarLista() {
        listaTrabajos.add(new TrabajosVo("Constructor","Pedro", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("Albañil","Juan", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("dsfds","Carla", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("Constrvdfgvductor","Sofia", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("jiudasnj","Elisa", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("fdsjkfvndsjn","Maria", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("jcdjnvjw","Paco", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("nhfuihfidsjfkd","Ana", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("asfhdsiujfodsjfjkdsbvfbds","Paola", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("hdsuhcdsj","Daniela", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));
        listaTrabajos.add(new TrabajosVo("jcdjsndsvlmdsl","Maria José", "Realizacion de trabajos al exterior de casa como jardines, albercas", R.drawable.constructor));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
