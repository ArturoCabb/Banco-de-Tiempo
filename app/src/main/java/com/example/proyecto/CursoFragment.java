package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import java.util.ArrayList;

public class CursoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerCurso;
    ArrayList<CursoVo> listaCurso;

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

        listaCurso = new ArrayList<>();
        recyclerCurso.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        CursoAdapter adapter = new CursoAdapter(listaCurso);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EjecucionTrabajoActivity.class);
                intent.putExtra("nombreTrabajo", "Constructor");
                intent.putExtra("nombreTrabajador", "Pedro");
                intent.putExtra("descripcionTrabajo", "Realizacion de trabajos al exterior de casa como jardines, albercas");
                intent.putExtra("imgProfile", R.drawable.constructor);
                getActivity().startActivity(intent);
            }
        });

        recyclerCurso.setAdapter(adapter);
        return view;
    }

    private void llenarLista() {
        listaCurso.add(new CursoVo("Constructor","Pedro", "20 min", R.drawable.constructor));
        listaCurso.add(new CursoVo("Constructor","Pedro", "20 min", R.drawable.constructor));
        listaCurso.add(new CursoVo("Constructor","Pedro", "20 min", R.drawable.constructor));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}