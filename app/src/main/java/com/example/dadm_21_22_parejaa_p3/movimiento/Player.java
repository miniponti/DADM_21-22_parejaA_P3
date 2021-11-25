package com.example.dadm_21_22_parejaa_p3.movimiento;

import android.view.View;
import android.widget.TextView;
import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;
import com.example.dadm_21_22_parejaa_p3.engine.GameObject;
import com.example.dadm_21_22_parejaa_p3.input.InputController;

public abstract class Player extends GameObject{

    // VARIABLES ---------------------------------------------
    protected int maxX; // valor máximo para X
    protected int maxY; // valor máximo para Y
    protected int numVidas; // número de vidas del jugador
    protected double positionX; // posición en X
    protected double positionY; // posición en Y
    protected double speedFactor; // velocidad transformada a píxeles por milisegundo
    protected double pixelFactor; // tamaño elegido de pixel
    protected final TextView puntuacion;
    protected final TextView vidasTexto;
    // -------------------------------------------------------

    // CONSTRUCTOR
    public Player(final View view){
        pixelFactor = 1;
        numVidas = 3;
        maxX = view.getWidth() - view.getPaddingRight() - view.getPaddingLeft();
        maxY = view.getHeight() - view.getPaddingTop() - view.getPaddingBottom();

        speedFactor = pixelFactor * 100d / 1000d;
        puntuacion = view.findViewById(R.id.txt_score);
        vidasTexto = view.findViewById(R.id.txt_life);
    }

    // INICIALIZA LA POSICION DEL JUGADOR A LA MITAD DE LA PANTALLA
    @Override
    public void startGame() {
        positionX = maxX / 2;
        positionY = maxY / 2;
    }

    // CUANDO SE ACTUALIZA SE PASA LA INFORMACIÓN DEL INPUT
    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        updatePosition(elapsedMillis, gameEngine.ge_inputController);
        //checkFiring(elapsedMillis, gameEngine);
    }

    // FUNCIÓN QUE ACTUALIZA LA POSICIÓN DEL JUGADOR
    private void updatePosition(long elapsedMillis, InputController inputController) {
        positionX += speedFactor * inputController.horizontalFactor * elapsedMillis;
        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > maxX) {
            positionX = maxX;
        }
        positionY += speedFactor * inputController.verticalFactor * elapsedMillis;
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }
    }

    @Override
    public void onDraw() {
        vidasTexto.setText("VIDAS: " + numVidas);
    }
}
