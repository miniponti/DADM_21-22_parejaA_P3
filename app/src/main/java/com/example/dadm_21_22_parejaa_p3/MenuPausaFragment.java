package com.example.dadm_21_22_parejaa_p3;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class MenuPausaFragment extends Fragment {

    Button botonSalir;

    public MenuPausaFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu_pausa, container, false);

        // BOTON SALIR DEL JUEGO
        botonSalir = (Button) root.findViewById(R.id.btn_salir);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MenuPrincipal.class);
                startActivity(intent);
            }
        });

        return  root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}