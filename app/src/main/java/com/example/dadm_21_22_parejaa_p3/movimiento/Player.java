package com.example.dadm_21_22_parejaa_p3.movimiento;

import android.view.View;
import android.widget.TextView;
import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;
import com.example.dadm_21_22_parejaa_p3.engine.GameObject;
import com.example.dadm_21_22_parejaa_p3.input.InputController;

public class Player extends GameObject{

    // VARIABLES ---------------------------------------------
    private int maxX; // valor máximo para X
    private int maxY; // valor máximo para Y
    private double positionX; // posición en X
    private double positionY; // posición en Y
    private double speedFactor; // velocidad transformada a píxeles por milisegundo
    private double pixelFactor; // tamaño elegido de pixel
    //private final TextView playerScore;
    // -------------------------------------------------------

    // CONSTRUCTOR
    public Player(final View view){
        pixelFactor = view.getHeight() / 400d;
        maxX = view.getWidth() - view.getPaddingRight() - view.getPaddingLeft();
        maxY = view.getHeight() - view.getPaddingTop() - view.getPaddingBottom();
        speedFactor = pixelFactor * 100d / 1000d; // We want to move at 100px per second on a 400px tall screen

        //playerScore = (TextView) view.findViewById(R.id.txt_score);
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
        //textView.setText("["+(int) (positionX)+","+(int) (positionY)+"]");
    }
}
