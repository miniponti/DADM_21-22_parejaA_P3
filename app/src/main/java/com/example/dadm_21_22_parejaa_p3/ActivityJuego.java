package com.example.dadm_21_22_parejaa_p3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class ActivityJuego extends AppCompatActivity {

    FragmentContainerView fragmentContainerView;
    ImageButton botonPausa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_juego);

        // encontrar fragment container
        fragmentContainerView = (FragmentContainerView) findViewById(R.id.fragmentContainerView);
        FragmentManager FM = getSupportFragmentManager();
        FM.beginTransaction().replace(R.id.fragmentContainerView, new MenuPausaFragment(), "FRAGMENT_PAUSA");
        fragmentContainerView.setVisibility(View.GONE);

        // boton pausa
        botonPausa = (ImageButton) findViewById(R.id.btn_pausa);
        botonPausa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (fragmentContainerView.getVisibility() == View.GONE){
                    fragmentContainerView.setVisibility(View.VISIBLE);
                }
                else{
                    fragmentContainerView.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fragmentContainerView.getVisibility() == View.GONE){
            fragmentContainerView.setVisibility(View.VISIBLE);
        }
    }

}