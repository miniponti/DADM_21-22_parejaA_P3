package com.example.dadm_21_22_parejaa_p3.input;

import android.view.MotionEvent;
import android.view.View;
import com.example.dadm_21_22_parejaa_p3.R;

public class Joystick extends InputController{

    // coordenadas donde se ha pulsado la pantalla
    private float startingPositionX;
    private float startingPositionY;

    // radio del joystick virtual
    private final double maxDistance;

    // CONSTRUCTOR
    public Joystick(View view) {
        view.findViewById(R.id.joystick_main).setOnTouchListener(new JoystickTouchListener());
        view.findViewById(R.id.joystick_touch).setOnTouchListener(new FireButtonTouchListener());
        double pixelFactor = view.getHeight() / 400d;
        maxDistance = 50*pixelFactor;
    }

    // CONTROLADOR DEL JOYSTICK
    private class JoystickTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int action = event.getActionMasked();

            // SI SE PULSA LA PANTALLA, SE TOMAN LAS COORDENADAS ORIGEN
            if (action == MotionEvent.ACTION_DOWN) {
                startingPositionX = event.getX(0);
                startingPositionY = event.getY(0);
            }
            // SI DEJA DE PULSARSE, SE RESETEAN LOS FACTORES
            else if (action == MotionEvent.ACTION_UP) {
                horizontalFactor = 0;
                verticalFactor = 0;
            }
            // SI SE MUEVE SE CALCULA CUANTO
            else if (action == MotionEvent.ACTION_MOVE) {
                horizontalFactor = (event.getX(0) - startingPositionX) / maxDistance;
                if (horizontalFactor > 1) {
                    horizontalFactor = 1;
                }
                else if (horizontalFactor < -1) {
                    horizontalFactor = -1;
                }
                verticalFactor = (event.getY(0) - startingPositionY) / maxDistance;
                if (verticalFactor > 1) {
                    verticalFactor = 1;
                }
                else if (verticalFactor < -1) {
                    verticalFactor = -1;
                }
            }
            return true;
        }
    }

    // CONTROLADOR DEL BOTON DE DISPARO
    private class FireButtonTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getActionMasked();
            if (action == MotionEvent.ACTION_DOWN) {
                isFiring = true;
            }
            else if (action == MotionEvent.ACTION_UP) {
                isFiring = false;
            }
            return true;
        }
    }



}
