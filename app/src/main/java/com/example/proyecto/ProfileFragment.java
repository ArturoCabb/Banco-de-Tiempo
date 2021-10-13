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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();



        TextView perfil = view.findViewById(R.id.tvProfileNombre);
        TextView location = view.findViewById(R.id.tvLocationProfile);
        TextView email = view.findViewById(R.id.tvProfileEmail);
        TextView edadtv = view.findViewById(R.id.tvProfileEdad);
        TextView localLocation = view.findViewById(R.id.tvProfileLocation);
        TextView hours = view.findViewById(R.id.tvProfileHorasTrabajadas);
        TextView phone = view.findViewById(R.id.tvProfileTelefono);
        TextView workHours = view.findViewById(R.id.tvProfileHorarioTrabajo);
        Button btnVerificar = view.findViewById(R.id.btProfileVerificar);
        Button btnMyWork = view.findViewById(R.id.btProfileCV);
        Button btnEditProfile = view.findViewById(R.id.btProfileEdit);
        Button btnLogout = view.findViewById(R.id.btProfileLogout);

        String user_id = mAuth.getCurrentUser().getUid();

        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();

        mdatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String nombre = snapshot.child(user_id).child("nombre").getValue().toString();
                    perfil.setText(nombre);

                    String edad = snapshot.child(user_id).child("edad").getValue().toString();
                    edadtv.setText(edad);
                    email.setText(mAuth.getCurrentUser().getEmail());

                    String telefono = snapshot.child(user_id).child("telefono").getValue().toString();
                    phone.setText(telefono);

                    String localidad = snapshot.child(user_id).child("localidad").getValue().toString();
                    location.setText(localidad);

                }else{

                    perfil.setText("Error snapshot");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                perfil.setText("Error");



            }
        });


        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Verificar.class);
                startActivity(in);
            }
        });

        btnMyWork.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent in = new Intent(getActivity(), CartaVidaActivity.class);
                startActivity(in);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ModificarPerfilActivity.class);
                startActivity(in);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}

