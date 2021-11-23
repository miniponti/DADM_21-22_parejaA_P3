package com.example.dadm_21_22_parejaa_p3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.dadm_21_22_parejaa_p3.MenuPausaFragment;
import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;
import com.example.dadm_21_22_parejaa_p3.input.InputController;

public class ActivityJuego extends AppCompatActivity {

    private FragmentContainerView fragmentContainerView;
    private ImageButton botonPausa;
    private MediaPlayer mp_btn; // efecto al hacer click

    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_juego);

        gameEngine = new GameEngine(this);
        gameEngine.setInputController(new InputController());

        // encontrar fragment container para el menú de pausa
        fragmentContainerView = (FragmentContainerView) findViewById(R.id.fragmentContainerView);
        FragmentManager FM = getSupportFragmentManager();
        FM.beginTransaction().replace(R.id.fragmentContainerView, new MenuPausaFragment(), "FRAGMENT_PAUSA");
        fragmentContainerView.setVisibility(View.GONE);

        // EFECTO SONIDO BOTON
        mp_btn = MediaPlayer.create(getApplicationContext(), R.raw.sfx_botones);

        // boton pausa
        botonPausa = (ImageButton) findViewById(R.id.btn_pausa);
        botonPausa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // suena el efecto de sonido
                mp_btn.start();

                // se hace visible / invisible
                if (fragmentContainerView.getVisibility() == View.GONE){
                    fragmentContainerView.setVisibility(View.VISIBLE);
                }
                else{
                    fragmentContainerView.setVisibility(View.GONE);
                }
            }
        });

    }

    // AJUSTA LA VISIBILIDAD DEL FRAGMENTO DEPENDIENDO DE SI EL JUEGO ESTÁ PAUSADO O NO
    @Override
    protected void onPause() {
        super.onPause();

        gameEngine.ge_pauseGame();

        if (fragmentContainerView.getVisibility() == View.GONE){
            fragmentContainerView.setVisibility(View.VISIBLE);
        }

    }

}