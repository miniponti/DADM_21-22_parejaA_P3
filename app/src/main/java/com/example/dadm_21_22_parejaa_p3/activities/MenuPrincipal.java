package com.example.dadm_21_22_parejaa_p3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dadm_21_22_parejaa_p3.R;

public class MenuPrincipal extends AppCompatActivity {

    MediaPlayer mp_bgm; // musica de fondo
    MediaPlayer mp_btn; // efecto al hacer click

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu_principal);

        // CANCION MENU
        mp_bgm = MediaPlayer.create(getApplicationContext(), R.raw.bgm_menus);
        mp_bgm.start();
        mp_bgm.setLooping(true);

        // EFECTO SONIDO BOTON
        mp_btn = MediaPlayer.create(getApplicationContext(), R.raw.sfx_botones);

        // BOTON EMPEZAR
        Button btn_empezar = findViewById(R.id.btn_empezar);
        btn_empezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // suena el efecto de sonido
                mp_btn.start();

                // se cambia de actividad
                Intent intent = new Intent(MenuPrincipal.this,ActivityJuego.class);
                startActivity(intent);

                // se para la musica
                mp_bgm.stop();
            }
        });
    }
}