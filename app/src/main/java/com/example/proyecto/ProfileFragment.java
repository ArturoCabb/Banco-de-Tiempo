package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment(){

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
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView perfil = view.findViewById(R.id.tvProfileNombre);
        TextView location = view.findViewById(R.id.tvLocationProfile);
        TextView email = view.findViewById(R.id.tvProfileEmail);
        TextView localLocation = view.findViewById(R.id.tvProfileLocation);
        TextView hours = view.findViewById(R.id.tvProfileHorasTrabajadas);
        TextView phone = view.findViewById(R.id.tvProfileTelefono);
        TextView workHours = view.findViewById(R.id.tvProfileHorarioTrabajo);
        Button btnVerificar = view.findViewById(R.id.btProfileVerificar);
        Button btnMyWork = view.findViewById(R.id.btProfileCV);
        Button btnEditProfile = view.findViewById(R.id.btProfileEdit);
        Button btnLogout = view.findViewById(R.id.btProfileCerrarSesion);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Verificar.class);
                startActivity(in);
            }
        });

        btnMyWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), CartaVidaActivity.class);
                startActivity(in);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}

